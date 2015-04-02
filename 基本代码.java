1 界面UI
2 数据存储
3 一步步深入了解View


3 一步步深入了解View
http://blog.csdn.net/guolin_blog/article/details/12921889
View的工作原理以及自定义View的方法
相信接触Android久一点的朋友对于LayoutInflater一定不会陌生，都会知道它主要是用于加载布局的。
而刚接触Android的朋友可能对LayoutInflater不怎么熟悉，因为加载布局的任务通常都是在Activity中调用setContentView()方法来完成的。
其实setContentView()方法的内部也是使用LayoutInflater来加载布局的，只不过这部分源码是internal的，不太容易查看到。
那么今天我们就来把LayoutInflater的工作流程仔细地剖析一遍，也许还能解决掉某些困扰你心头多年的疑惑。
先来看一下LayoutInflater的基本用法吧，它的用法非常简单，首先需要获取到LayoutInflater的实例，有两种方法可以获取到，第一种写法如下：

	LayoutInflater layoutInflater = LayoutInflater.from(context);

当然，还有另外一种写法也可以完成同样的效果：

	LayoutInflater layoutInflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

其实第一种就是第二种的简单写法，只是Android给我们做了一下封装而已。得到了LayoutInflater的实例之后就可以调用它的inflate()方法
来加载布局了，如下所示：

	layoutInflater.inflate(resourceId, root);  

inflate()方法一般接收两个参数，第一个参数就是要加载的布局id，第二个参数是指给该布局的外部再嵌套一层父布局，如果不需要就直接传null。
这样就成功成功创建了一个布局的实例，之后再将它添加到指定的位置就可以显示出来了。
下面我们就通过一个非常简单的小例子，来更加直观地看一下LayoutInflater的用法。比如说当前有一个项目，
其中MainActivity对应的布局文件叫做activity_main.xml，代码如下所示：
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/main_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

	</LinearLayout>
	
这个布局文件的内容非常简单，只有一个空的LinearLayout，里面什么控件都没有，因此界面上应该不会显示任何东西。
那么接下来我们再定义一个布局文件，给它取名为button_layout.xml，代码如下所示：
	<Button xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Button" >

	</Button>

这个布局文件也非常简单，只有一个Button按钮而已。现在我们要想办法，如何通过LayoutInflater来将button_layout这个布局添加到
主布局文件的LinearLayout中。根据刚刚介绍的用法，修改MainActivity中的代码，如下所示：
	public class MainActivity extends Activity {

	private LinearLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View buttonLayout = layoutInflater.inflate(R.layout.button_layout, null);
		mainLayout.addView(buttonLayout);
	}

	}

可以看到，这里先是获取到了LayoutInflater的实例，然后调用它的inflate()方法来加载button_layout这个布局，
最后调用LinearLayout的addView()方法将它添加到LinearLayout中。
现在可以运行一下程序，结果如下图所示：
Button在界面上显示出来了！说明我们确实是借助LayoutInflater成功将button_layout这个布局添加到LinearLayout中了。
LayoutInflater技术广泛应用于需要动态添加View的时候，比如在ScrollView和ListView中，经常都可以看到LayoutInflater的身影。
当然，仅仅只是介绍了如何使用LayoutInflater显然是远远无法满足大家的求知欲的，知其然也要知其所以然，接下来我们就从源码的角度上
看一看LayoutInflater到底是如何工作的。
不管你是使用的哪个inflate()方法的重载，最终都会辗转调用到LayoutInflater的如下代码中：
	public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
    synchronized (mConstructorArgs) {
        final AttributeSet attrs = Xml.asAttributeSet(parser);
        mConstructorArgs[0] = mContext;
        View result = root;
        try {
            int type;
            while ((type = parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
            }
            if (type != XmlPullParser.START_TAG) {
                throw new InflateException(parser.getPositionDescription()
                        + ": No start tag found!");
            }
            final String name = parser.getName();
            if (TAG_MERGE.equals(name)) {
                if (root == null || !attachToRoot) {
                    throw new InflateException("merge can be used only with a valid "
                            + "ViewGroup root and attachToRoot=true");
                }
                rInflate(parser, root, attrs);
            } else {
                View temp = createViewFromTag(name, attrs);
                ViewGroup.LayoutParams params = null;
                if (root != null) {
                    params = root.generateLayoutParams(attrs);
                    if (!attachToRoot) {
                        temp.setLayoutParams(params);
                    }
                }
                rInflate(parser, temp, attrs);
                if (root != null && attachToRoot) {
                    root.addView(temp, params);
                }
                if (root == null || !attachToRoot) {
                    result = temp;
                }
            }
        } catch (XmlPullParserException e) {
            InflateException ex = new InflateException(e.getMessage());
            ex.initCause(e);
            throw ex;
        } catch (IOException e) {
            InflateException ex = new InflateException(
                    parser.getPositionDescription()
                    + ": " + e.getMessage());
            ex.initCause(e);
            throw ex;
        }
        return result;
    }
	}

从这里我们就可以清楚地看出，LayoutInflater其实就是使用Android提供的pull解析方式来解析布局文件的。
不熟悉pull解析方式的朋友可以网上搜一下，教程很多，我就不细讲了，这里我们注意看下第23行，调用了createViewFromTag()这个方法，
并把节点名和参数传了进去。看到这个方法名，我们就应该能猜到，它是用于根据节点名来创建View对象的。确实如此，
在createViewFromTag()方法的内部又会去调用createView()方法，然后使用反射的方式创建出View的实例并返回。
当然，这里只是创建出了一个根布局的实例而已，接下来会在第31行调用rInflate()方法来循环遍历这个根布局下的子元素，代码如下所示：

	private void rInflate(XmlPullParser parser, View parent, final AttributeSet attrs)
        throws XmlPullParserException, IOException {
    final int depth = parser.getDepth();
    int type;
    while (((type = parser.next()) != XmlPullParser.END_TAG ||
            parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {
        if (type != XmlPullParser.START_TAG) {
            continue;
        }
        final String name = parser.getName();
        if (TAG_REQUEST_FOCUS.equals(name)) {
            parseRequestFocus(parser, parent);
        } else if (TAG_INCLUDE.equals(name)) {
            if (parser.getDepth() == 0) {
                throw new InflateException("<include /> cannot be the root element");
            }
            parseInclude(parser, parent, attrs);
        } else if (TAG_MERGE.equals(name)) {
            throw new InflateException("<merge /> must be the root element");
        } else {
            final View view = createViewFromTag(name, attrs);
            final ViewGroup viewGroup = (ViewGroup) parent;
            final ViewGroup.LayoutParams params = viewGroup.generateLayoutParams(attrs);
            rInflate(parser, view, attrs);
            viewGroup.addView(view, params);
        }
    }
    parent.onFinishInflate();
	}

可以看到，在第21行同样是createViewFromTag()方法来创建View的实例，然后还会在第24行递归调用rInflate()方法来查找这个View下的子元素，
每次递归完成后则将这个View添加到父布局当中。
这样的话，把整个布局文件都解析完成后就形成了一个完整的DOM结构，最终会把最顶层的根布局返回，至此inflate()过程全部结束。
比较细心的朋友也许会注意到，inflate()方法还有个接收三个参数的方法重载，结构如下：
	    inflate(int resource, ViewGroup root, boolean attachToRoot) 
那么这第三个参数attachToRoot又是什么意思呢？其实如果你仔细去阅读上面的源码应该可以自己分析出答案，这里我先将结论说一下吧，
感兴趣的朋友可以再阅读一下源码，校验我的结论是否正确。
1. 如果root为null，attachToRoot将失去作用，设置任何值都没有意义。
2. 如果root不为null，attachToRoot设为true，则会在加载的布局文件的最外层再嵌套一层root布局。
3. 如果root不为null，attachToRoot设为false，则root参数失去作用。
4. 在不设置attachToRoot参数的情况下，如果root不为null，attachToRoot参数默认为true。
好了，现在对LayoutInflater的工作原理和流程也搞清楚了，你该满足了吧。额。。。。还嫌这个例子中的按钮看起来有点小，想要调大一些？
那简单的呀，修改button_layout.xml中的代码，如下所示：
	<Button xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="300dp"
    android:layout_height="80dp"
    android:text="Button" >

	</Button>
这里我们将按钮的宽度改成300dp，高度改成80dp，这样够大了吧？现在重新运行一下程序来观察效果。咦？怎么按钮还是原来的大小，
没有任何变化！是不是按钮仍然不够大，再改大一点呢？还是没有用！
其实这里不管你将Button的layout_width和layout_height的值修改成多少，都不会有任何效果的，因为这两个值现在已经完全失去了作用。
平时我们经常使用layout_width和layout_height来设置View的大小，并且一直都能正常工作，就好像这两个属性确实是用于设置View的大小的。
而实际上则不然，它们其实是用于设置View在布局中的大小的，也就是说，首先View必须存在于一个布局中，之后如果将layout_width
设置成match_parent表示让View的宽度填充满布局，如果设置成wrap_content表示让View的宽度刚好可以包含其内容
，如果设置成具体的数值则View的宽度会变成相应的数值。这也是为什么这两个属性叫作layout_width和layout_height，而不是width和height。
再来看一下我们的button_layout.xml吧，很明显Button这个控件目前不存在于任何布局当中，所以layout_width和layout_height这两个属性
理所当然没有任何作用。那么怎样修改才能让按钮的大小改变呢？解决方法其实有很多种，最简单的方式就是在Button的外面再嵌套一层布局，
如下所示：

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <Button
        android:layout_width="300dp"
        android:layout_height="80dp"
        android:text="Button" >
    </Button>

	</RelativeLayout>
可以看到，这里我们又加入了一个RelativeLayout，此时的Button存在与RelativeLayout之中，layout_width和layout_height属性也就有作用了。
当然，处于最外层的RelativeLayout，它的layout_width和layout_height则会失去作用。现在重新运行一下程序，结果如下图所示：
OK！按钮的终于可以变大了，这下总算是满足大家的要求了吧。

看到这里，也许有些朋友心中会有一个巨大的疑惑。不对呀！平时在Activity中指定布局文件的时候，最外层的那个布局是可以指定大小的呀，
layout_width和layout_height都是有作用的。确实，这主要是因为，在setContentView()方法中，Android会自动在布局文件的最外层
再嵌套一个FrameLayout，所以layout_width和layout_height属性才会有效果。那么我们来证实一下吧，修改MainActivity中的代码，如下所示：

public class MainActivity extends Activity {

	private LinearLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		ViewParent viewParent = mainLayout.getParent();
		Log.d("TAG", "the parent of mainLayout is " + viewParent);
	}

}

非常正确！LinearLayout的父布局确实是一个FrameLayout，而这个FrameLayout就是由系统自动帮我们添加上的。
说到这里，虽然setContentView()方法大家都会用，但实际上Android界面显示的原理要比我们所看到的东西复杂得多。
任何一个Activity中显示的界面其实主要都由两部分组成，标题栏和内容布局。标题栏就是在很多界面顶部显示的那部分内容，
比如刚刚我们的那个例子当中就有标题栏，可以在代码中控制让它是否显示。而内容布局就是一个FrameLayout，这个布局的id叫作content，
我们调用setContentView()方法时所传入的布局其实就是放到这个FrameLayout中的，这也是为什么这个方法名叫作setContentView()，
而不是叫setView()。
最后再附上一张Activity窗口的组成图吧，以便于大家更加直观地理解：

相信每个Android程序员都知道，我们每天的开发工作当中都在不停地跟View打交道，Android中的任何一个布局、任何一个控件其实都是
直接或间接继承自View的，如TextView、Button、ImageView、ListView等。这些控件虽然是Android系统本身就提供好的，
我们只需要拿过来使用就可以了，但你知道它们是怎样被绘制到屏幕上的吗？多知道一些总是没有坏处的，
那么我们赶快进入到本篇文章的正题内容吧。
要知道，任何一个视图都不可能凭空突然出现在屏幕上，它们都是要经过非常科学的绘制流程后才能显示出来的。
每一个视图的绘制过程都必须经历三个最主要的阶段，即onMeasure()、onLayout()和onDraw()，下面我们逐个对这三个阶段展开进行探讨。

一. onMeasure()
measure是测量的意思，那么onMeasure()方法顾名思义就是用于测量视图的大小的。View系统的绘制流程会从ViewRoot的performTraversals()
方法中开始，在其内部调用View的measure()方法。measure()方法接收两个参数，widthMeasureSpec和heightMeasureSpec，
这两个值分别用于确定视图的宽度和高度的规格和大小。

MeasureSpec的值由specSize和specMode共同组成的，其中specSize记录的是大小，specMode记录的是规格。specMode一共有三种类型，如下所示：
1. EXACTLY
表示父视图希望子视图的大小应该是由specSize的值来决定的，系统默认会按照这个规则来设置子视图的大小，
开发人员当然也可以按照自己的意愿设置成任意的大小。
2. AT_MOST
表示子视图最多只能是specSize中指定的大小，开发人员应该尽可能小得去设置这个视图，并且保证不会超过specSize。
系统默认会按照这个规则来设置子视图的大小，开发人员当然也可以按照自己的意愿设置成任意的大小。
3. UNSPECIFIED
表示开发人员可以将视图按照自己的意愿设置成任意的大小，没有任何限制。这种情况比较少见，不太会用到。

那么你可能会有疑问了，widthMeasureSpec和heightMeasureSpec这两个值又是从哪里得到的呢？通常情况下，
这两个值都是由父视图经过计算后传递给子视图的，说明父视图会在一定程度上决定子视图的大小。但是最外层的根视图，
它的widthMeasureSpec和heightMeasureSpec又是从哪里得到的呢？这就需要去分析ViewRoot中的源码了，
观察performTraversals()方法可以发现如下代码：

	childWidthMeasureSpec = getRootMeasureSpec(desiredWindowWidth, lp.width);
	childHeightMeasureSpec = getRootMeasureSpec(desiredWindowHeight, lp.height);

可以看到，这里调用了getRootMeasureSpec()方法去获取widthMeasureSpec和heightMeasureSpec的值，注意方法中传入的参数，
其中lp.width和lp.height在创建ViewGroup实例的时候就被赋值了，它们都等于MATCH_PARENT。然后看下getRootMeasureSpec()方法中的代码，
如下所示：

	private int getRootMeasureSpec(int windowSize, int rootDimension) {
    int measureSpec;
    switch (rootDimension) {
    case ViewGroup.LayoutParams.MATCH_PARENT:
        measureSpec = MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.EXACTLY);
        break;
    case ViewGroup.LayoutParams.WRAP_CONTENT:
        measureSpec = MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.AT_MOST);
        break;
    default:
        measureSpec = MeasureSpec.makeMeasureSpec(rootDimension, MeasureSpec.EXACTLY);
        break;
    }
    return measureSpec;
	}

可以看到，这里使用了MeasureSpec.makeMeasureSpec()方法来组装一个MeasureSpec，当rootDimension参数等于MATCH_PARENT的时候，
MeasureSpec的specMode就等于EXACTLY，当rootDimension等于WRAP_CONTENT的时候，MeasureSpec的specMode就等于AT_MOST。
并且MATCH_PARENT和WRAP_CONTENT时的specSize都是等于windowSize的，也就意味着根视图总是会充满全屏的。
介绍了这么多MeasureSpec相关的内容，接下来我们看下View的measure()方法里面的代码吧，如下所示：

	public final void measure(int widthMeasureSpec, int heightMeasureSpec) {
    if ((mPrivateFlags & FORCE_LAYOUT) == FORCE_LAYOUT ||
            widthMeasureSpec != mOldWidthMeasureSpec ||
            heightMeasureSpec != mOldHeightMeasureSpec) {
        mPrivateFlags &= ~MEASURED_DIMENSION_SET;
        if (ViewDebug.TRACE_HIERARCHY) {
            ViewDebug.trace(this, ViewDebug.HierarchyTraceType.ON_MEASURE);
        }
        onMeasure(widthMeasureSpec, heightMeasureSpec);
        if ((mPrivateFlags & MEASURED_DIMENSION_SET) != MEASURED_DIMENSION_SET) {
            throw new IllegalStateException("onMeasure() did not set the"
                    + " measured dimension by calling"
                    + " setMeasuredDimension()");
        }
        mPrivateFlags |= LAYOUT_REQUIRED;
    }
    mOldWidthMeasureSpec = widthMeasureSpec;
    mOldHeightMeasureSpec = heightMeasureSpec;
	}

注意观察，measure()这个方法是final的，因此我们无法在子类中去重写这个方法，说明Android是不允许我们改变View的measure框架的。
然后在第9行调用了onMeasure()方法，这里才是真正去测量并设置View大小的地方，默认会调用getDefaultSize()方法来获取视图的大小，
如下所示：
	public static int getDefaultSize(int size, int measureSpec) {
    int result = size;
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);
    switch (specMode) {
    case MeasureSpec.UNSPECIFIED:
        result = size;
        break;
    case MeasureSpec.AT_MOST:
    case MeasureSpec.EXACTLY:
        result = specSize;
        break;
    }
    return result;
	}

这里传入的measureSpec是一直从measure()方法中传递过来的。然后调用MeasureSpec.getMode()方法可以解析出specMode，
调用MeasureSpec.getSize()方法可以解析出specSize。接下来进行判断，如果specMode等于AT_MOST或EXACTLY就返回specSize，
这也是系统默认的行为。之后会在onMeasure()方法中调用setMeasuredDimension()方法来设定测量出的大小，这样一次measure过程就结束了。
当然，一个界面的展示可能会涉及到很多次的measure，因为一个布局中一般都会包含多个子视图，每个视图都需要经历一次measure过程。
ViewGroup中定义了一个measureChildren()方法来去测量子视图的大小，如下所示：
	protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) {
    final int size = mChildrenCount;
    final View[] children = mChildren;
    for (int i = 0; i < size; ++i) {
        final View child = children[i];
        if ((child.mViewFlags & VISIBILITY_MASK) != GONE) {
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }
	}
这里首先会去遍历当前布局下的所有子视图，然后逐个调用measureChild()方法来测量相应子视图的大小，如下所示：
	protected void measureChild(View child, int parentWidthMeasureSpec,
        int parentHeightMeasureSpec) {
    final LayoutParams lp = child.getLayoutParams();
    final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
            mPaddingLeft + mPaddingRight, lp.width);
    final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
            mPaddingTop + mPaddingBottom, lp.height);
    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
	}

可以看到，在第4行和第6行分别调用了getChildMeasureSpec()方法来去计算子视图的MeasureSpec，计算的依据就是布局文件中定义
的MATCH_PARENT、WRAP_CONTENT等值，这个方法的内部细节就不再贴出。然后在第8行调用子视图的measure()方法，并把计算出的MeasureSpec传递进去，
之后的流程就和前面所介绍的一样了。
当然，onMeasure()方法是可以重写的，也就是说，如果你不想使用系统默认的测量方式，可以按照自己的意愿进行定制，比如：
	public class MyView extends View {

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(200, 200);
	}

	}
这样的话就把View默认的测量流程覆盖掉了，不管在布局文件中定义MyView这个视图的大小是多少，最终在界面上显示的大小都将会是200*200。
需要注意的是，在setMeasuredDimension()方法调用之后，我们才能使用getMeasuredWidth()和getMeasuredHeight()来获取视图测量出的宽高，
以此之前调用这两个方法得到的值都会是0。
由此可见，视图大小的控制是由父视图、布局文件、以及视图本身共同完成的，父视图会提供给子视图参考的大小，而开发人员可以在XML文件中指定视图的大小，
然后视图本身会对最终的大小进行拍板。
到此为止，我们就把视图绘制流程的第一阶段分析完了。

二. onLayout()

measure过程结束后，视图的大小就已经测量好了，接下来就是layout的过程了。正如其名字所描述的一样，这个方法是用于给视图进行布局的，也就是确定视图的位置。
ViewRoot的performTraversals()方法会在measure结束后继续执行，并调用View的layout()方法来执行此过程，如下所示：
	
	host.layout(0, 0, host.mMeasuredWidth, host.mMeasuredHeight);  

layout()方法接收四个参数，分别代表着左、上、右、下的坐标，当然这个坐标是相对于当前视图的父视图而言的。可以看到，这里还把刚才测量出的宽度和高度
传到了layout()方法中。那么我们来看下layout()方法中的代码是什么样的吧，如下所示：

	public void layout(int l, int t, int r, int b) {
    int oldL = mLeft;
    int oldT = mTop;
    int oldB = mBottom;
    int oldR = mRight;
    boolean changed = setFrame(l, t, r, b);
    if (changed || (mPrivateFlags & LAYOUT_REQUIRED) == LAYOUT_REQUIRED) {
        if (ViewDebug.TRACE_HIERARCHY) {
            ViewDebug.trace(this, ViewDebug.HierarchyTraceType.ON_LAYOUT);
        }
        onLayout(changed, l, t, r, b);
        mPrivateFlags &= ~LAYOUT_REQUIRED;
        if (mOnLayoutChangeListeners != null) {
            ArrayList<OnLayoutChangeListener> listenersCopy =
                    (ArrayList<OnLayoutChangeListener>) mOnLayoutChangeListeners.clone();
            int numListeners = listenersCopy.size();
            for (int i = 0; i < numListeners; ++i) {
                listenersCopy.get(i).onLayoutChange(this, l, t, r, b, oldL, oldT, oldR, oldB);
            }
        }
    }
    mPrivateFlags &= ~FORCE_LAYOUT;
	}

在layout()方法中，首先会调用setFrame()方法来判断视图的大小是否发生过变化，以确定有没有必要对当前的视图进行重绘，同时还会在这里把传递过来的四个参数
分别赋值给mLeft、mTop、mRight和mBottom这几个变量。接下来会在第11行调用onLayout()方法，正如onMeasure()方法中的默认行为一样，也许你已经迫不及待地
想知道onLayout()方法中的默认行为是什么样的了。进入onLayout()方法，咦？怎么这是个空方法，一行代码都没有？！

没错，View中的onLayout()方法就是一个空方法，因为onLayout()过程是为了确定视图在布局中所在的位置，而这个操作应该是由布局来完成的，
即父视图决定子视图的显示位置。既然如此，我们来看下ViewGroup中的onLayout()方法是怎么写的吧，代码如下：

	@Override
	protected abstract void onLayout(boolean changed, int l, int t, int r, int b);

可以看到，ViewGroup中的onLayout()方法竟然是一个抽象方法，这就意味着所有ViewGroup的子类都必须重写这个方法。没错，像LinearLayout、RelativeLayout等布局，
都是重写了这个方法，然后在内部按照各自的规则对子视图进行布局的。由于LinearLayout和RelativeLayout的布局规则都比较复杂，就不单独拿出来进行分析了，
这里我们尝试自定义一个布局，借此来更深刻地理解onLayout()的过程。

	public class SimpleLayout extends ViewGroup {

	public SimpleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (getChildCount() > 0) {
			View childView = getChildAt(0);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (getChildCount() > 0) {
			View childView = getChildAt(0);
			childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
		}
	}

	}

代码非常的简单，我们来看下具体的逻辑吧。你已经知道，onMeasure()方法会在onLayout()方法之前调用，因此这里在onMeasure()方法中判断SimpleLayout中
是否有包含一个子视图，如果有的话就调用measureChild()方法来测量出子视图的大小。
接着在onLayout()方法中同样判断SimpleLayout是否有包含一个子视图，然后调用这个子视图的layout()方法来确定它在SimpleLayout布局中的位置，
这里传入的四个参数依次是0、0、childView.getMeasuredWidth()和childView.getMeasuredHeight()，分别代表着子视图在SimpleLayout中左上右下四个点的坐标。
其中，调用childView.getMeasuredWidth()和childView.getMeasuredHeight()方法得到的值就是在onMeasure()方法中测量出的宽和高。
这样就已经把SimpleLayout这个布局定义好了，下面就是在XML文件中使用它了，如下所示：

	<com.example.viewtest.SimpleLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
	
    <ImageView 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_launcher"
        />
    
	</com.example.viewtest.SimpleLayout>

可以看到，我们能够像使用普通的布局文件一样使用SimpleLayout，只是注意它只能包含一个子视图，多余的子视图会被舍弃掉。
这里SimpleLayout中包含了一个ImageView，并且ImageView的宽高都是wrap_content。现在运行一下程序，结果如下图所示：
OK！ImageView成功已经显示出来了，并且显示的位置也正是我们所期望的。如果你想改变ImageView显示的位置，只需要改变childView.layout()方法的四个参数就行了。
在onLayout()过程结束后，我们就可以调用getWidth()方法和getHeight()方法来获取视图的宽高了。说到这里，我相信很多朋友长久以来都会有一个疑问，
getWidth()方法和getMeasureWidth()方法到底有什么区别呢？它们的值好像永远都是相同的。其实它们的值之所以会相同基本都是因为布局设计者的编码习惯非常好，
实际上它们之间的差别还是挺大的。
首先getMeasureWidth()方法在measure()过程结束后就可以获取到了，而getWidth()方法要在layout()过程结束后才能获取到。另外，
getMeasureWidth()方法中的值是通过setMeasuredDimension()方法来进行设置的，而getWidth()方法中的值则是通过视图右边的坐标减去左边的坐标计算出来的。
观察SimpleLayout中onLayout()方法的代码，这里给子视图的layout()方法传入的四个参数分别是0、0、childView.getMeasuredWidth()和childView.getMeasuredHeight()，
因此getWidth()方法得到的值就是childView.getMeasuredWidth() - 0 = childView.getMeasuredWidth() ，
所以此时getWidth()方法和getMeasuredWidth() 得到的值就是相同的，但如果你将onLayout()方法中的代码进行如下修改：
	@Override
protected void onLayout(boolean changed, int l, int t, int r, int b) {
	if (getChildCount() > 0) {
		View childView = getChildAt(0);
		childView.layout(0, 0, 200, 200);
	}
}

这样getWidth()方法得到的值就是200 - 0 = 200，不会再和getMeasuredWidth()的值相同了。当然这种做法充分不尊重measure()过程计算出的结果，
通常情况下是不推荐这么写的。getHeight()与getMeasureHeight()方法之间的关系同上，就不再重复分析了。
到此为止，我们把视图绘制流程的第二阶段也分析完了。

三. onDraw()

measure和layout的过程都结束后，接下来就进入到draw的过程了。同样，根据名字你就能够判断出，在这里才真正地开始对视图进行绘制。
ViewRoot中的代码会继续执行并创建出一个Canvas对象，然后调用View的draw()方法来执行具体的绘制工作。
draw()方法内部的绘制过程总共可以分为六步，其中第二步和第五步在一般情况下很少用到，因此这里我们只分析简化后的绘制过程。代码如下所示：
	public void draw(Canvas canvas) {
	if (ViewDebug.TRACE_HIERARCHY) {
	    ViewDebug.trace(this, ViewDebug.HierarchyTraceType.DRAW);
	}
	final int privateFlags = mPrivateFlags;
	final boolean dirtyOpaque = (privateFlags & DIRTY_MASK) == DIRTY_OPAQUE &&
	        (mAttachInfo == null || !mAttachInfo.mIgnoreDirtyState);
	mPrivateFlags = (privateFlags & ~DIRTY_MASK) | DRAWN;
	// Step 1, draw the background, if needed
	int saveCount;
	if (!dirtyOpaque) {
	    final Drawable background = mBGDrawable;
	    if (background != null) {
	        final int scrollX = mScrollX;
	        final int scrollY = mScrollY;
	        if (mBackgroundSizeChanged) {
	            background.setBounds(0, 0,  mRight - mLeft, mBottom - mTop);
	            mBackgroundSizeChanged = false;
	        }
	        if ((scrollX | scrollY) == 0) {
	            background.draw(canvas);
	        } else {
	            canvas.translate(scrollX, scrollY);
	            background.draw(canvas);
	            canvas.translate(-scrollX, -scrollY);
	        }
	    }
	}
	final int viewFlags = mViewFlags;
	boolean horizontalEdges = (viewFlags & FADING_EDGE_HORIZONTAL) != 0;
	boolean verticalEdges = (viewFlags & FADING_EDGE_VERTICAL) != 0;
	if (!verticalEdges && !horizontalEdges) {
	    // Step 3, draw the content
	    if (!dirtyOpaque) onDraw(canvas);
	    // Step 4, draw the children
	    dispatchDraw(canvas);
	    // Step 6, draw decorations (scrollbars)
	    onDrawScrollBars(canvas);
	    // we're done...
	    return;
	}
	}

可以看到，第一步是从第9行代码开始的，这一步的作用是对视图的背景进行绘制。这里会先得到一个mBGDrawable对象，
然后根据layout过程确定的视图位置来设置背景的绘制区域，之后再调用Drawable的draw()方法来完成背景的绘制工作。
那么这个mBGDrawable对象是从哪里来的呢？其实就是在XML中通过android:background属性设置的图片或颜色。当然你也可以在代码中通过setBackgroundColor()、
setBackgroundResource()等方法进行赋值。
接下来的第三步是在第34行执行的，这一步的作用是对视图的内容进行绘制。可以看到，这里去调用了一下onDraw()方法，
那么onDraw()方法里又写了什么代码呢？进去一看你会发现，原来又是个空方法啊。其实也可以理解，因为每个视图的内容部分肯定都是各不相同的，
这部分的功能交给子类来去实现也是理所当然的。
第三步完成之后紧接着会执行第四步，这一步的作用是对当前视图的所有子视图进行绘制。但如果当前的视图没有子视图，那么也就不需要进行绘制了。
因此你会发现View中的dispatchDraw()方法又是一个空方法，而ViewGroup的dispatchDraw()方法中就会有具体的绘制代码。
以上都执行完后就会进入到第六步，也是最后一步，这一步的作用是对视图的滚动条进行绘制。那么你可能会奇怪，当前的视图又不一定是ListView或者ScrollView，
为什么要绘制滚动条呢？其实不管是Button也好，TextView也好，任何一个视图都是有滚动条的，只是一般情况下我们都没有让它显示出来而已。
绘制滚动条的代码逻辑也比较复杂，这里就不再贴出来了，因为我们的重点是第三步过程。
通过以上流程分析，相信大家已经知道，View是不会帮我们绘制内容部分的，因此需要每个视图根据想要展示的内容来自行绘制。
如果你去观察TextView、ImageView等类的源码，你会发现它们都有重写onDraw()这个方法，并且在里面执行了相当不少的绘制逻辑。
绘制的方式主要是借助Canvas这个类，它会作为参数传入到onDraw()方法中，供给每个视图使用。Canvas这个类的用法非常丰富，
基本可以把它当成一块画布，在上面绘制任意的东西，那么我们就来尝试一下吧。
这里简单起见，我只是创建一个非常简单的视图，并且用Canvas随便绘制了一点东西，代码如下所示：
	public class MyView extends View {

	private Paint mPaint;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mPaint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		mPaint.setColor(Color.BLUE);
		mPaint.setTextSize(20);
		String text = "Hello View";
		canvas.drawText(text, 0, getHeight() / 2, mPaint);
	}
	}

可以看到，我们创建了一个自定义的MyView继承自View，并在MyView的构造函数中创建了一个Paint对象。Paint就像是一个画笔一样，
配合着Canvas就可以进行绘制了。这里我们的绘制逻辑比较简单，在onDraw()方法中先是把画笔设置成黄色，然后调用Canvas的drawRect()方法绘制一个矩形。
然后在把画笔设置成蓝色，并调整了一下文字的大小，然后调用drawText()方法绘制了一段文字。

就这么简单，一个自定义的视图就已经写好了，现在可以在XML中加入这个视图，如下所示：
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.example.viewtest.MyView 
        android:layout_width="200dp"
        android:layout_height="100dp"
        />

	</LinearLayout>

图中显示的内容也正是MyView这个视图的内容部分了。由于我们没给MyView设置背景，因此这里看不出来View自动绘制的背景效果。
当然了Canvas的用法还有很多很多，这里我不可能把Canvas的所有用法都列举出来，剩下的就要靠大家自行去研究和学习了。
到此为止，我们把视图绘制流程的第三阶段也分析完了。整个视图的绘制过程就全部结束了，你现在是不是对View的理解更加深刻了呢？
感兴趣的朋友可以继续阅读 Android视图状态及重绘流程分析，带你一步步深入了解View(三) 。

相信大家在平时使用View的时候都会发现它是有状态的，比如说有一个按钮，普通状态下是一种效果，但是当手指按下的时候就会变成另外一种效果，
这样才会给人产生一种点击了按钮的感觉。当然了，这种效果相信几乎所有的Android程序员都知道该如何实现，但是我们既然是深入了解View，
那么自然也应该知道它背后的实现原理应该是什么样的，今天就让我们来一起探究一下吧。

一、视图状态
视图状态的种类非常多，一共有十几种类型，不过多数情况下我们只会使用到其中的几种，因此这里我们也就只去分析最常用的几种视图状态。
1. enabled
表示当前视图是否可用。可以调用setEnable()方法来改变视图的可用状态，传入true表示可用，传入false表示不可用。
它们之间最大的区别在于，不可用的视图是无法响应onTouch事件的。
2. focused
表示当前视图是否获得到焦点。通常情况下有两种方法可以让视图获得焦点，即通过键盘的上下左右键切换视图，以及调用requestFocus()方法。
而现在的Android手机几乎都没有键盘了，因此基本上只可以使用requestFocus()这个办法来让视图获得焦点了。
而requestFocus()方法也不能保证一定可以让视图获得焦点，它会有一个布尔值的返回值，如果返回true说明获得焦点成功，返回false说明获得焦点失败。
一般只有视图在focusable和focusable in touch mode同时成立的情况下才能成功获取焦点，比如说EditText。
3. window_focused
表示当前视图是否处于正在交互的窗口中，这个值由系统自动决定，应用程序不能进行改变。
4. selected
表示当前视图是否处于选中状态。一个界面当中可以有多个视图处于选中状态，调用setSelected()方法能够改变视图的选中状态，传入true表示选中，传入false表示未选中。
5. pressed
表示当前视图是否处于按下状态。可以调用setPressed()方法来对这一状态进行改变，传入true表示按下，传入false表示未按下。
通常情况下这个状态都是由系统自动赋值的，但开发者也可以自己调用这个方法来进行改变。

我们可以在项目的drawable目录下创建一个selector文件，在这里配置每种状态下视图对应的背景图片。比如创建一个compose_bg.xml文件，在里面编写如下代码：

	<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:drawable="@drawable/compose_pressed" android:state_pressed="true"></item>
    <item android:drawable="@drawable/compose_pressed" android:state_focused="true"></item>
    <item android:drawable="@drawable/compose_normal"></item>

	</selector>

这段代码就表示，当视图处于正常状态的时候就显示compose_normal这张背景图，当视图获得到焦点或者被按下的时候就显示compose_pressed这张背景图。
创建好了这个selector文件后，我们就可以在布局或代码中使用它了，比如将它设置为某个按钮的背景图，如下所示：

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >
    
	<Button 
	    android:id="@+id/compose"
	    android:layout_width="60dp"
	    android:layout_height="40dp"
	    android:layout_gravity="center_horizontal"
	    android:background="@drawable/compose_bg"
	    />
    
</LinearLayout>

这样我们就用一个非常简单的方法实现了按钮按下的效果，但是它的背景原理到底是怎样的呢？这就又要从源码的层次上进行分析了。

不知不觉中，带你一步步深入了解View系列的文章已经写到第四篇了，回顾一下，我们一共学习了LayoutInflater的原理分析、视图的绘制流程、视图的状态及重绘等知识，
算是把View中很多重要的知识点都涉及到了。如果你还没有看过我前面的几篇文章，建议先去阅读一下，多了解一些原理方面的东西。

如果说要按类型来划分的话，自定义View的实现方式大概可以分为三种，自绘控件、组合控件、以及继承控件。
那么下面我们就来依次学习一下，每种方式分别是如何自定义View的。

一、自绘控件
自绘控件的意思就是，这个View上所展现的内容全部都是我们自己绘制出来的。绘制的代码是写在onDraw()方法中的，
而这部分内容我们已经在 Android视图绘制流程完全解析，带你一步步深入了解View(二) 中学习过了。
下面我们准备来自定义一个计数器View，这个View可以响应用户的点击事件，并自动记录一共点击了多少次。新建一个CounterView继承自View，代码如下所示：

	public class CounterView extends View implements OnClickListener {

	private Paint mPaint;
	
	private Rect mBounds;

	private int mCount;
	
	public CounterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBounds = new Rect();
		setOnClickListener(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setColor(Color.BLUE);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		mPaint.setColor(Color.YELLOW);
		mPaint.setTextSize(30);
		String text = String.valueOf(mCount);
		mPaint.getTextBounds(text, 0, text.length(), mBounds);
		float textWidth = mBounds.width();
		float textHeight = mBounds.height();
		canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2
				+ textHeight / 2, mPaint);
	}

	@Override
	public void onClick(View v) {
		mCount++;
		invalidate();
	}

	}
可以看到，首先我们在CounterView的构造函数中初始化了一些数据，并给这个View的本身注册了点击事件，这样当CounterView被点击的时候，
onClick()方法就会得到调用。而onClick()方法中的逻辑就更加简单了，只是对mCount这个计数器加1，然后调用invalidate()方法。
通过 Android视图状态及重绘流程分析，带你一步步深入了解View(三) 这篇文章的学习我们都已经知道，调用invalidate()方法会导致视图进行重绘，
因此onDraw()方法在稍后就将会得到调用。
既然CounterView是一个自绘视图，那么最主要的逻辑当然就是写在onDraw()方法里的了，下面我们就来仔细看一下。
这里首先是将Paint画笔设置为蓝色，然后调用Canvas的drawRect()方法绘制了一个矩形，这个矩形也就可以当作是CounterView的背景图吧。
接着将画笔设置为黄色，准备在背景上面绘制当前的计数，注意这里先是调用了getTextBounds()方法来获取到文字的宽度和高度，
然后调用了drawText()方法去进行绘制就可以了。
这样，一个自定义的View就已经完成了，并且目前这个CounterView是具备自动计数功能的。那么剩下的问题就是如何让这个View在界面上显示出来了，
其实这也非常简单，我们只需要像使用普通的控件一样来使用CounterView就可以了。比如在布局文件中加入如下代码：

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.example.customview.CounterView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_centerInParent="true" />

	</RelativeLayout>

可以看到，这里我们将CounterView放入了一个RelativeLayout中，然后可以像使用普通控件来给CounterView指定各种属性，
比如通过layout_width和layout_height来指定CounterView的宽高，通过android:layout_centerInParent来指定它在布局里居中显示。
只不过需要注意，自定义的View在使用的时候一定要写出完整的包名，不然系统将无法找到这个View。
好了，就是这么简单，接下来我们可以运行一下程序，并不停地点击CounterView，效果如下图所示。

怎么样？是不是感觉自定义View也并不是什么高级的技术，简单几行代码就可以实现了。当然了，这个CounterView功能非常简陋，只有一个计数功能，
因此只需几行代码就足够了，当你需要绘制比较复杂的View时，还是需要很多技巧的。

二、组合控件
组合控件的意思就是，我们并不需要自己去绘制视图上显示的内容，而只是用系统原生的控件就好了，但我们可以将几个系统原生的控件组合到一起，
这样创建出的控件就被称为组合控件。
举个例子来说，标题栏就是个很常见的组合控件，很多界面的头部都会放置一个标题栏，标题栏上会有个返回按钮和标题，
点击按钮后就可以返回到上一个界面。那么下面我们就来尝试去实现这样一个标题栏控件。
新建一个title.xml布局文件，代码如下所示：

	<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:background="#ffcb05" >

    <Button
        android:id="@+id/button_left"
        android:layout_width="60dp"
        android:layout_height="40dp"
        android:layout_centerVertical="true"
        android:layout_marginLeft="5dp"
        android:background="@drawable/back_button"
        android:text="Back"
        android:textColor="#fff" />

    <TextView
        android:id="@+id/title_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="This is Title"
        android:textColor="#fff"
        android:textSize="20sp" />

</RelativeLayout>

在这个布局文件中，我们首先定义了一个RelativeLayout作为背景布局，然后在这个布局里定义了一个Button和一个TextView，
Button就是标题栏中的返回按钮，TextView就是标题栏中的显示的文字。

接下来创建一个TitleView继承自FrameLayout，代码如下所示：

public class TitleView extends FrameLayout {

	private Button leftButton;

	private TextView titleText;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this);
		titleText = (TextView) findViewById(R.id.title_text);
		leftButton = (Button) findViewById(R.id.button_left);
		leftButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) getContext()).finish();
			}
		});
	}

	public void setTitleText(String text) {
		titleText.setText(text);
	}

	public void setLeftButtonText(String text) {
		leftButton.setText(text);
	}

	public void setLeftButtonListener(OnClickListener l) {
		leftButton.setOnClickListener(l);
	}

}

TitleView中的代码非常简单，在TitleView的构建方法中，我们调用了LayoutInflater的inflate()方法来加载刚刚定义的title.xml布局，
这部分内容我们已经在 Android LayoutInflater原理分析，带你一步步深入了解View(一) 这篇文章中学习过了。
接下来调用findViewById()方法获取到了返回按钮的实例，然后在它的onClick事件中调用finish()方法来关闭当前的Activity，也就相当于实现返回功能了。
另外，为了让TitleView有更强地扩展性，我们还提供了setTitleText()、setLeftButtonText()、setLeftButtonListener()等方法，
分别用于设置标题栏上的文字、返回按钮上的文字、以及返回按钮的点击事件。
到了这里，一个自定义的标题栏就完成了，那么下面又到了如何引用这个自定义View的部分，其实方法基本都是相同的，在布局文件中添加如下代码：

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.example.customview.TitleView
        android:id="@+id/title_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </com.example.customview.TitleView>

	</RelativeLayout>
这样就成功将一个标题栏控件引入到布局文件中了，运行一下程序，效果如下图所示：
现在点击一下Back按钮，就可以关闭当前的Activity了。如果你想要修改标题栏上显示的内容，或者返回按钮的默认事件，
只需要在Activity中通过findViewById()方法得到TitleView的实例，然后调用setTitleText()、setLeftButtonText()、setLeftButtonListener()等方法进行设置就OK了。

三、继承控件
继承控件的意思就是，我们并不需要自己重头去实现一个控件，只需要去继承一个现有的控件，然后在这个控件上增加一些新的功能，就可以形成一个自定义的控件了。
这种自定义控件的特点就是不仅能够按照我们的需求加入相应的功能，还可以保留原生控件的所有功能，
比如 Android PowerImageView实现，可以播放动画的强大ImageView 这篇文章中介绍的PowerImageView就是一个典型的继承控件。
为了能够加深大家对这种自定义View方式的理解，下面我们再来编写一个新的继承控件。ListView相信每一个Android程序员都一定使用过，
这次我们准备对ListView进行扩展，加入在ListView上滑动就可以显示出一个删除按钮，点击按钮就会删除相应数据的功能。
首先需要准备一个删除按钮的布局，新建delete_button.xml文件，代码如下所示：

<?xml version="1.0" encoding="utf-8"?>
<Button xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/delete_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:background="@drawable/delete_button" >

</Button>
这个布局文件很简单，只有一个按钮而已，并且我们给这个按钮指定了一张删除背景图。
接着创建MyListView继承自ListView，这就是我们自定义的View了，代码如下所示：

	public class MyListView extends ListView implements OnTouchListener,
		OnGestureListener {

	private GestureDetector gestureDetector;

	private OnDeleteListener listener;

	private View deleteButton;

	private ViewGroup itemLayout;

	private int selectedItem;

	private boolean isDeleteShown;

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gestureDetector = new GestureDetector(getContext(), this);
		setOnTouchListener(this);
	}

	public void setOnDeleteListener(OnDeleteListener l) {
		listener = l;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (isDeleteShown) {
			itemLayout.removeView(deleteButton);
			deleteButton = null;
			isDeleteShown = false;
			return false;
		} else {
			return gestureDetector.onTouchEvent(event);
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		if (!isDeleteShown) {
			selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
		}
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
			deleteButton = LayoutInflater.from(getContext()).inflate(
					R.layout.delete_button, null);
			deleteButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					itemLayout.removeView(deleteButton);
					deleteButton = null;
					isDeleteShown = false;
					listener.onDelete(selectedItem);
				}
			});
			itemLayout = (ViewGroup) getChildAt(selectedItem
					- getFirstVisiblePosition());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params.addRule(RelativeLayout.CENTER_VERTICAL);
			itemLayout.addView(deleteButton, params);
			isDeleteShown = true;
		}
		return false;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}
	
	public interface OnDeleteListener {

		void onDelete(int index);

	}

	}

由于代码逻辑比较简单，我就没有加注释。这里在MyListView的构造方法中创建了一个GestureDetector的实例用于监听手势，
然后给MyListView注册了touch监听事件。然后在onTouch()方法中进行判断，如果删除按钮已经显示了，就将它移除掉，
如果删除按钮没有显示，就使用GestureDetector来处理当前手势。
当手指按下时，会调用OnGestureListener的onDown()方法，在这里通过pointToPosition()方法来判断出当前选中的是ListView的哪一行。
当手指快速滑动时，会调用onFling()方法，在这里会去加载delete_button.xml这个布局，然后将删除按钮添加到当前选中的那一行item上。
注意，我们还给删除按钮添加了一个点击事件，当点击了删除按钮时就会回调onDeleteListener的onDelete()方法，在回调方法中应该去处理具体的删除操作。
好了，自定义View的功能到此就完成了，接下来我们需要看一下如何才能使用这个自定义View。首先需要创建一个ListView子项的布局文件，
新建my_list_view_item.xml，代码如下所示：

	<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:descendantFocusability="blocksDescendants"
    android:orientation="vertical" >

    <TextView
        android:id="@+id/text_view"
        android:layout_width="wrap_content"
        android:layout_height="50dp"
        android:layout_centerVertical="true"
        android:gravity="left|center_vertical"
        android:textColor="#000" />

</RelativeLayout>

然后创建一个适配器MyAdapter，在这个适配器中去加载my_list_view_item布局，代码如下所示：

	public class MyAdapter extends ArrayAdapter<String> {

	public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.my_list_view_item, null);
		} else {
			view = convertView;
		}
		TextView textView = (TextView) view.findViewById(R.id.text_view);
		textView.setText(getItem(position));
		return view;
	}

	}

到这里就基本已经完工了，下面在程序的主布局文件里面引入MyListView这个控件，如下所示：
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.example.customview.MyListView
        android:id="@+id/my_list_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </com.example.customview.MyListView>

	</RelativeLayout>

最后在Activity中初始化MyListView中的数据，并处理了onDelete()方法的删除逻辑，代码如下所示：

	public class MainActivity extends Activity {

	private MyListView myListView;

	private MyAdapter adapter;

	private List<String> contentList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initList();
		myListView = (MyListView) findViewById(R.id.my_list_view);
		myListView.setOnDeleteListener(new OnDeleteListener() {
			@Override
			public void onDelete(int index) {
				contentList.remove(index);
				adapter.notifyDataSetChanged();
			}
		});
		adapter = new MyAdapter(this, 0, contentList);
		myListView.setAdapter(adapter);
	}

	private void initList() {
		contentList.add("Content Item 1");
		contentList.add("Content Item 2");
		contentList.add("Content Item 3");
		contentList.add("Content Item 4");
		contentList.add("Content Item 5");
		contentList.add("Content Item 6");
		contentList.add("Content Item 7");
		contentList.add("Content Item 8");
		contentList.add("Content Item 9");
		contentList.add("Content Item 10");
		contentList.add("Content Item 11");
		contentList.add("Content Item 12");
		contentList.add("Content Item 13");
		contentList.add("Content Item 14");
		contentList.add("Content Item 15");
		contentList.add("Content Item 16");
		contentList.add("Content Item 17");
		contentList.add("Content Item 18");
		contentList.add("Content Item 19");
		contentList.add("Content Item 20");
	}

	}

这样就把整个例子的代码都完成了，现在运行一下程序，会看到MyListView可以像ListView一样，正常显示所有的数据，
但是当你用手指在MyListView的某一行上快速滑动时，就会有一个删除按钮显示出来，如下图所示：