1 ����UI
2 ���ݴ洢
3 һ���������˽�View


3 һ���������˽�View
http://blog.csdn.net/guolin_blog/article/details/12921889
View�Ĺ���ԭ���Լ��Զ���View�ķ���
���ŽӴ�Android��һ������Ѷ���LayoutInflaterһ������İ��������֪������Ҫ�����ڼ��ز��ֵġ�
���սӴ�Android�����ѿ��ܶ�LayoutInflater����ô��Ϥ����Ϊ���ز��ֵ�����ͨ��������Activity�е���setContentView()��������ɵġ�
��ʵsetContentView()�������ڲ�Ҳ��ʹ��LayoutInflater�����ز��ֵģ�ֻ�����ⲿ��Դ����internal�ģ���̫���ײ鿴����
��ô�������Ǿ�����LayoutInflater�Ĺ���������ϸ������һ�飬Ҳ���ܽ����ĳЩ��������ͷ������ɻ�
������һ��LayoutInflater�Ļ����÷��ɣ������÷��ǳ��򵥣�������Ҫ��ȡ��LayoutInflater��ʵ���������ַ������Ի�ȡ������һ��д�����£�

	LayoutInflater layoutInflater = LayoutInflater.from(context);

��Ȼ����������һ��д��Ҳ�������ͬ����Ч����

	LayoutInflater layoutInflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

��ʵ��һ�־��ǵڶ��ֵļ�д����ֻ��Android����������һ�·�װ���ѡ��õ���LayoutInflater��ʵ��֮��Ϳ��Ե�������inflate()����
�����ز����ˣ�������ʾ��

	layoutInflater.inflate(resourceId, root);  

inflate()����һ�����������������һ����������Ҫ���صĲ���id���ڶ���������ָ���ò��ֵ��ⲿ��Ƕ��һ�㸸���֣��������Ҫ��ֱ�Ӵ�null��
�����ͳɹ��ɹ�������һ�����ֵ�ʵ����֮���ٽ�����ӵ�ָ����λ�þͿ�����ʾ�����ˡ�
�������Ǿ�ͨ��һ���ǳ��򵥵�С���ӣ�������ֱ�۵ؿ�һ��LayoutInflater���÷�������˵��ǰ��һ����Ŀ��
����MainActivity��Ӧ�Ĳ����ļ�����activity_main.xml������������ʾ��
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/main_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

	</LinearLayout>
	
��������ļ������ݷǳ��򵥣�ֻ��һ���յ�LinearLayout������ʲô�ؼ���û�У���˽�����Ӧ�ò�����ʾ�κζ�����
��ô�����������ٶ���һ�������ļ�������ȡ��Ϊbutton_layout.xml������������ʾ��
	<Button xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Button" >

	</Button>

��������ļ�Ҳ�ǳ��򵥣�ֻ��һ��Button��ť���ѡ���������Ҫ��취�����ͨ��LayoutInflater����button_layout���������ӵ�
�������ļ���LinearLayout�С����ݸոս��ܵ��÷����޸�MainActivity�еĴ��룬������ʾ��
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

���Կ������������ǻ�ȡ����LayoutInflater��ʵ����Ȼ���������inflate()����������button_layout������֣�
������LinearLayout��addView()����������ӵ�LinearLayout�С�
���ڿ�������һ�³��򣬽������ͼ��ʾ��
Button�ڽ�������ʾ�����ˣ�˵������ȷʵ�ǽ���LayoutInflater�ɹ���button_layout���������ӵ�LinearLayout���ˡ�
LayoutInflater�����㷺Ӧ������Ҫ��̬���View��ʱ�򣬱�����ScrollView��ListView�У����������Կ���LayoutInflater����Ӱ��
��Ȼ������ֻ�ǽ��������ʹ��LayoutInflater��Ȼ��ԶԶ�޷������ҵ���֪���ģ�֪��ȻҲҪ֪������Ȼ�����������Ǿʹ�Դ��ĽǶ���
��һ��LayoutInflater��������ι����ġ�
��������ʹ�õ��ĸ�inflate()���������أ����ն���շת���õ�LayoutInflater�����´����У�
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

���������ǾͿ�������ؿ�����LayoutInflater��ʵ����ʹ��Android�ṩ��pull������ʽ�����������ļ��ġ�
����Ϥpull������ʽ�����ѿ���������һ�£��̳̺ܶ࣬�ҾͲ�ϸ���ˣ���������ע�⿴�µ�23�У�������createViewFromTag()���������
���ѽڵ����Ͳ������˽�ȥ��������������������Ǿ�Ӧ���ܲµ����������ڸ��ݽڵ���������View����ġ�ȷʵ��ˣ�
��createViewFromTag()�������ڲ��ֻ�ȥ����createView()������Ȼ��ʹ�÷���ķ�ʽ������View��ʵ�������ء�
��Ȼ������ֻ�Ǵ�������һ�������ֵ�ʵ�����ѣ����������ڵ�31�е���rInflate()������ѭ����������������µ���Ԫ�أ�����������ʾ��

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

���Կ������ڵ�21��ͬ����createViewFromTag()����������View��ʵ����Ȼ�󻹻��ڵ�24�еݹ����rInflate()�������������View�µ���Ԫ�أ�
ÿ�εݹ���ɺ������View��ӵ������ֵ��С�
�����Ļ��������������ļ���������ɺ���γ���һ��������DOM�ṹ�����ջ�����ĸ����ַ��أ�����inflate()����ȫ��������
�Ƚ�ϸ�ĵ�����Ҳ���ע�⵽��inflate()�������и��������������ķ������أ��ṹ���£�
	    inflate(int resource, ViewGroup root, boolean attachToRoot) 
��ô�����������attachToRoot����ʲô��˼�أ���ʵ�������ϸȥ�Ķ������Դ��Ӧ�ÿ����Լ��������𰸣��������Ƚ�����˵һ�°ɣ�
����Ȥ�����ѿ������Ķ�һ��Դ�룬У���ҵĽ����Ƿ���ȷ��
1. ���rootΪnull��attachToRoot��ʧȥ���ã������κ�ֵ��û�����塣
2. ���root��Ϊnull��attachToRoot��Ϊtrue������ڼ��صĲ����ļ����������Ƕ��һ��root���֡�
3. ���root��Ϊnull��attachToRoot��Ϊfalse����root����ʧȥ���á�
4. �ڲ�����attachToRoot����������£����root��Ϊnull��attachToRoot����Ĭ��Ϊtrue��
���ˣ����ڶ�LayoutInflater�Ĺ���ԭ�������Ҳ������ˣ���������˰ɡ��������������������еİ�ť�������е�С����Ҫ����һЩ��
�Ǽ򵥵�ѽ���޸�button_layout.xml�еĴ��룬������ʾ��
	<Button xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="300dp"
    android:layout_height="80dp"
    android:text="Button" >

	</Button>
�������ǽ���ť�Ŀ�ȸĳ�300dp���߶ȸĳ�80dp�����������˰ɣ�������������һ�³������۲�Ч�����ף���ô��ť����ԭ���Ĵ�С��
û���κα仯���ǲ��ǰ�ť��Ȼ�������ٸĴ�һ���أ�����û���ã�
��ʵ���ﲻ���㽫Button��layout_width��layout_height��ֵ�޸ĳɶ��٣����������κ�Ч���ģ���Ϊ������ֵ�����Ѿ���ȫʧȥ�����á�
ƽʱ���Ǿ���ʹ��layout_width��layout_height������View�Ĵ�С������һֱ���������������ͺ�������������ȷʵ����������View�Ĵ�С�ġ�
��ʵ������Ȼ��������ʵ����������View�ڲ����еĴ�С�ģ�Ҳ����˵������View���������һ�������У�֮�������layout_width
���ó�match_parent��ʾ��View�Ŀ����������֣�������ó�wrap_content��ʾ��View�Ŀ�ȸպÿ��԰���������
��������óɾ������ֵ��View�Ŀ�Ȼ�����Ӧ����ֵ����Ҳ��Ϊʲô���������Խ���layout_width��layout_height��������width��height��
������һ�����ǵ�button_layout.xml�ɣ�������Button����ؼ�Ŀǰ���������κβ��ֵ��У�����layout_width��layout_height����������
������Ȼû���κ����á���ô�����޸Ĳ����ð�ť�Ĵ�С�ı��أ����������ʵ�кܶ��֣���򵥵ķ�ʽ������Button��������Ƕ��һ�㲼�֣�
������ʾ��

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <Button
        android:layout_width="300dp"
        android:layout_height="80dp"
        android:text="Button" >
    </Button>

	</RelativeLayout>
���Կ��������������ּ�����һ��RelativeLayout����ʱ��Button������RelativeLayout֮�У�layout_width��layout_height����Ҳ���������ˡ�
��Ȼ������������RelativeLayout������layout_width��layout_height���ʧȥ���á�������������һ�³��򣬽������ͼ��ʾ��
OK����ť�����ڿ��Ա���ˣ����������������ҵ�Ҫ���˰ɡ�

�������Ҳ����Щ�������л���һ���޴���ɻ󡣲���ѽ��ƽʱ��Activity��ָ�������ļ���ʱ���������Ǹ������ǿ���ָ����С��ѽ��
layout_width��layout_height���������õġ�ȷʵ������Ҫ����Ϊ����setContentView()�����У�Android���Զ��ڲ����ļ��������
��Ƕ��һ��FrameLayout������layout_width��layout_height���ԲŻ���Ч������ô������֤ʵһ�°ɣ��޸�MainActivity�еĴ��룬������ʾ��

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

�ǳ���ȷ��LinearLayout�ĸ�����ȷʵ��һ��FrameLayout�������FrameLayout������ϵͳ�Զ�����������ϵġ�
˵�������ȻsetContentView()������Ҷ����ã���ʵ����Android������ʾ��ԭ��Ҫ�������������Ķ������ӵöࡣ
�κ�һ��Activity����ʾ�Ľ�����ʵ��Ҫ������������ɣ������������ݲ��֡������������ںܶ���涥����ʾ���ǲ������ݣ�
����ո����ǵ��Ǹ����ӵ��о��б������������ڴ����п��������Ƿ���ʾ�������ݲ��־���һ��FrameLayout��������ֵ�id����content��
���ǵ���setContentView()����ʱ������Ĳ�����ʵ���Ƿŵ����FrameLayout�еģ���Ҳ��Ϊʲô�������������setContentView()��
�����ǽ�setView()��
����ٸ���һ��Activity���ڵ����ͼ�ɣ��Ա��ڴ�Ҹ���ֱ�۵���⣺

����ÿ��Android����Ա��֪��������ÿ��Ŀ����������ж��ڲ�ͣ�ظ�View�򽻵���Android�е��κ�һ�����֡��κ�һ���ؼ���ʵ����
ֱ�ӻ��Ӽ̳���View�ģ���TextView��Button��ImageView��ListView�ȡ���Щ�ؼ���Ȼ��Androidϵͳ������ṩ�õģ�
����ֻ��Ҫ�ù���ʹ�þͿ����ˣ�����֪�����������������Ƶ���Ļ�ϵ��𣿶�֪��һЩ����û�л����ģ�
��ô���ǸϿ���뵽��ƪ���µ��������ݰɡ�
Ҫ֪�����κ�һ����ͼ��������ƾ��ͻȻ��������Ļ�ϣ����Ƕ���Ҫ�����ǳ���ѧ�Ļ������̺������ʾ�����ġ�
ÿһ����ͼ�Ļ��ƹ��̶����뾭����������Ҫ�Ľ׶Σ���onMeasure()��onLayout()��onDraw()����������������������׶�չ������̽�֡�

һ. onMeasure()
measure�ǲ�������˼����ôonMeasure()��������˼��������ڲ�����ͼ�Ĵ�С�ġ�Viewϵͳ�Ļ������̻��ViewRoot��performTraversals()
�����п�ʼ�������ڲ�����View��measure()������measure()������������������widthMeasureSpec��heightMeasureSpec��
������ֵ�ֱ�����ȷ����ͼ�Ŀ�Ⱥ͸߶ȵĹ��ʹ�С��

MeasureSpec��ֵ��specSize��specMode��ͬ��ɵģ�����specSize��¼���Ǵ�С��specMode��¼���ǹ��specModeһ�����������ͣ�������ʾ��
1. EXACTLY
��ʾ����ͼϣ������ͼ�Ĵ�СӦ������specSize��ֵ�������ģ�ϵͳĬ�ϻᰴ�������������������ͼ�Ĵ�С��
������Ա��ȻҲ���԰����Լ�����Ը���ó�����Ĵ�С��
2. AT_MOST
��ʾ����ͼ���ֻ����specSize��ָ���Ĵ�С��������ԱӦ�þ�����С��ȥ���������ͼ�����ұ�֤���ᳬ��specSize��
ϵͳĬ�ϻᰴ�������������������ͼ�Ĵ�С��������Ա��ȻҲ���԰����Լ�����Ը���ó�����Ĵ�С��
3. UNSPECIFIED
��ʾ������Ա���Խ���ͼ�����Լ�����Ը���ó�����Ĵ�С��û���κ����ơ���������Ƚ��ټ�����̫���õ���

��ô����ܻ��������ˣ�widthMeasureSpec��heightMeasureSpec������ֵ���Ǵ�����õ����أ�ͨ������£�
������ֵ�����ɸ���ͼ��������󴫵ݸ�����ͼ�ģ�˵������ͼ����һ���̶��Ͼ�������ͼ�Ĵ�С�����������ĸ���ͼ��
����widthMeasureSpec��heightMeasureSpec���Ǵ�����õ����أ������Ҫȥ����ViewRoot�е�Դ���ˣ�
�۲�performTraversals()�������Է������´��룺

	childWidthMeasureSpec = getRootMeasureSpec(desiredWindowWidth, lp.width);
	childHeightMeasureSpec = getRootMeasureSpec(desiredWindowHeight, lp.height);

���Կ��������������getRootMeasureSpec()����ȥ��ȡwidthMeasureSpec��heightMeasureSpec��ֵ��ע�ⷽ���д���Ĳ�����
����lp.width��lp.height�ڴ���ViewGroupʵ����ʱ��ͱ���ֵ�ˣ����Ƕ�����MATCH_PARENT��Ȼ����getRootMeasureSpec()�����еĴ��룬
������ʾ��

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

���Կ���������ʹ����MeasureSpec.makeMeasureSpec()��������װһ��MeasureSpec����rootDimension��������MATCH_PARENT��ʱ��
MeasureSpec��specMode�͵���EXACTLY����rootDimension����WRAP_CONTENT��ʱ��MeasureSpec��specMode�͵���AT_MOST��
����MATCH_PARENT��WRAP_CONTENTʱ��specSize���ǵ���windowSize�ģ�Ҳ����ζ�Ÿ���ͼ���ǻ����ȫ���ġ�
��������ô��MeasureSpec��ص����ݣ����������ǿ���View��measure()��������Ĵ���ɣ�������ʾ��

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

ע��۲죬measure()���������final�ģ���������޷���������ȥ��д���������˵��Android�ǲ��������Ǹı�View��measure��ܵġ�
Ȼ���ڵ�9�е�����onMeasure()�����������������ȥ����������View��С�ĵط���Ĭ�ϻ����getDefaultSize()��������ȡ��ͼ�Ĵ�С��
������ʾ��
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

���ﴫ���measureSpec��һֱ��measure()�����д��ݹ����ġ�Ȼ�����MeasureSpec.getMode()�������Խ�����specMode��
����MeasureSpec.getSize()�������Խ�����specSize�������������жϣ����specMode����AT_MOST��EXACTLY�ͷ���specSize��
��Ҳ��ϵͳĬ�ϵ���Ϊ��֮�����onMeasure()�����е���setMeasuredDimension()�������趨�������Ĵ�С������һ��measure���̾ͽ����ˡ�
��Ȼ��һ�������չʾ���ܻ��漰���ܶ�ε�measure����Ϊһ��������һ�㶼������������ͼ��ÿ����ͼ����Ҫ����һ��measure���̡�
ViewGroup�ж�����һ��measureChildren()������ȥ��������ͼ�Ĵ�С��������ʾ��
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
�������Ȼ�ȥ������ǰ�����µ���������ͼ��Ȼ���������measureChild()������������Ӧ����ͼ�Ĵ�С��������ʾ��
	protected void measureChild(View child, int parentWidthMeasureSpec,
        int parentHeightMeasureSpec) {
    final LayoutParams lp = child.getLayoutParams();
    final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
            mPaddingLeft + mPaddingRight, lp.width);
    final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
            mPaddingTop + mPaddingBottom, lp.height);
    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
	}

���Կ������ڵ�4�к͵�6�зֱ������getChildMeasureSpec()������ȥ��������ͼ��MeasureSpec����������ݾ��ǲ����ļ��ж���
��MATCH_PARENT��WRAP_CONTENT��ֵ������������ڲ�ϸ�ھͲ���������Ȼ���ڵ�8�е�������ͼ��measure()���������Ѽ������MeasureSpec���ݽ�ȥ��
֮������̾ͺ�ǰ�������ܵ�һ���ˡ�
��Ȼ��onMeasure()�����ǿ�����д�ģ�Ҳ����˵������㲻��ʹ��ϵͳĬ�ϵĲ�����ʽ�����԰����Լ�����Ը���ж��ƣ����磺
	public class MyView extends View {

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(200, 200);
	}

	}
�����Ļ��Ͱ�ViewĬ�ϵĲ������̸��ǵ��ˣ������ڲ����ļ��ж���MyView�����ͼ�Ĵ�С�Ƕ��٣������ڽ�������ʾ�Ĵ�С��������200*200��
��Ҫע����ǣ���setMeasuredDimension()��������֮�����ǲ���ʹ��getMeasuredWidth()��getMeasuredHeight()����ȡ��ͼ�������Ŀ�ߣ�
�Դ�֮ǰ���������������õ���ֵ������0��
�ɴ˿ɼ�����ͼ��С�Ŀ������ɸ���ͼ�������ļ����Լ���ͼ����ͬ��ɵģ�����ͼ���ṩ������ͼ�ο��Ĵ�С����������Ա������XML�ļ���ָ����ͼ�Ĵ�С��
Ȼ����ͼ���������յĴ�С�����İ塣
����Ϊֹ�����ǾͰ���ͼ�������̵ĵ�һ�׶η������ˡ�

��. onLayout()

measure���̽�������ͼ�Ĵ�С���Ѿ��������ˣ�����������layout�Ĺ����ˡ�������������������һ����������������ڸ���ͼ���в��ֵģ�Ҳ����ȷ����ͼ��λ�á�
ViewRoot��performTraversals()��������measure���������ִ�У�������View��layout()������ִ�д˹��̣�������ʾ��
	
	host.layout(0, 0, host.mMeasuredWidth, host.mMeasuredHeight);  

layout()���������ĸ��������ֱ���������ϡ��ҡ��µ����꣬��Ȼ�������������ڵ�ǰ��ͼ�ĸ���ͼ���Եġ����Կ��������ﻹ�ѸղŲ������Ŀ�Ⱥ͸߶�
������layout()�����С���ô����������layout()�����еĴ�����ʲô���İɣ�������ʾ��

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

��layout()�����У����Ȼ����setFrame()�������ж���ͼ�Ĵ�С�Ƿ������仯����ȷ����û�б�Ҫ�Ե�ǰ����ͼ�����ػ棬ͬʱ����������Ѵ��ݹ������ĸ�����
�ֱ�ֵ��mLeft��mTop��mRight��mBottom�⼸�����������������ڵ�11�е���onLayout()����������onMeasure()�����е�Ĭ����Ϊһ����Ҳ�����Ѿ��Ȳ�������
��֪��onLayout()�����е�Ĭ����Ϊ��ʲô�����ˡ�����onLayout()�������ף���ô���Ǹ��շ�����һ�д��붼û�У���

û��View�е�onLayout()��������һ���շ�������ΪonLayout()������Ϊ��ȷ����ͼ�ڲ��������ڵ�λ�ã����������Ӧ�����ɲ�������ɵģ�
������ͼ��������ͼ����ʾλ�á���Ȼ��ˣ�����������ViewGroup�е�onLayout()��������ôд�İɣ��������£�

	@Override
	protected abstract void onLayout(boolean changed, int l, int t, int r, int b);

���Կ�����ViewGroup�е�onLayout()������Ȼ��һ�����󷽷��������ζ������ViewGroup�����඼������д���������û����LinearLayout��RelativeLayout�Ȳ��֣�
������д�����������Ȼ�����ڲ����ո��ԵĹ��������ͼ���в��ֵġ�����LinearLayout��RelativeLayout�Ĳ��ֹ��򶼱Ƚϸ��ӣ��Ͳ������ó������з����ˣ�
�������ǳ����Զ���һ�����֣����������̵����onLayout()�Ĺ��̡�

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

����ǳ��ļ򵥣����������¾�����߼��ɡ����Ѿ�֪����onMeasure()��������onLayout()����֮ǰ���ã����������onMeasure()�������ж�SimpleLayout��
�Ƿ��а���һ������ͼ������еĻ��͵���measureChild()����������������ͼ�Ĵ�С��
������onLayout()������ͬ���ж�SimpleLayout�Ƿ��а���һ������ͼ��Ȼ������������ͼ��layout()������ȷ������SimpleLayout�����е�λ�ã�
���ﴫ����ĸ�����������0��0��childView.getMeasuredWidth()��childView.getMeasuredHeight()���ֱ����������ͼ��SimpleLayout�����������ĸ�������ꡣ
���У�����childView.getMeasuredWidth()��childView.getMeasuredHeight()�����õ���ֵ������onMeasure()�����в������Ŀ�͸ߡ�
�������Ѿ���SimpleLayout������ֶ�����ˣ����������XML�ļ���ʹ�����ˣ�������ʾ��

	<com.example.viewtest.SimpleLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
	
    <ImageView 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_launcher"
        />
    
	</com.example.viewtest.SimpleLayout>

���Կ����������ܹ���ʹ����ͨ�Ĳ����ļ�һ��ʹ��SimpleLayout��ֻ��ע����ֻ�ܰ���һ������ͼ�����������ͼ�ᱻ��������
����SimpleLayout�а�����һ��ImageView������ImageView�Ŀ�߶���wrap_content����������һ�³��򣬽������ͼ��ʾ��
OK��ImageView�ɹ��Ѿ���ʾ�����ˣ�������ʾ��λ��Ҳ���������������ġ��������ı�ImageView��ʾ��λ�ã�ֻ��Ҫ�ı�childView.layout()�������ĸ����������ˡ�
��onLayout()���̽��������ǾͿ��Ե���getWidth()������getHeight()��������ȡ��ͼ�Ŀ���ˡ�˵����������źܶ����ѳ�������������һ�����ʣ�
getWidth()������getMeasureWidth()����������ʲô�����أ����ǵ�ֵ������Զ������ͬ�ġ���ʵ���ǵ�ֵ֮���Ի���ͬ����������Ϊ��������ߵı���ϰ�߷ǳ��ã�
ʵ��������֮��Ĳ����ͦ��ġ�
����getMeasureWidth()������measure()���̽�����Ϳ��Ի�ȡ���ˣ���getWidth()����Ҫ��layout()���̽�������ܻ�ȡ�������⣬
getMeasureWidth()�����е�ֵ��ͨ��setMeasuredDimension()�������������õģ���getWidth()�����е�ֵ����ͨ����ͼ�ұߵ������ȥ��ߵ������������ġ�
�۲�SimpleLayout��onLayout()�����Ĵ��룬���������ͼ��layout()����������ĸ������ֱ���0��0��childView.getMeasuredWidth()��childView.getMeasuredHeight()��
���getWidth()�����õ���ֵ����childView.getMeasuredWidth() - 0 = childView.getMeasuredWidth() ��
���Դ�ʱgetWidth()������getMeasuredWidth() �õ���ֵ������ͬ�ģ�������㽫onLayout()�����еĴ�����������޸ģ�
	@Override
protected void onLayout(boolean changed, int l, int t, int r, int b) {
	if (getChildCount() > 0) {
		View childView = getChildAt(0);
		childView.layout(0, 0, 200, 200);
	}
}

����getWidth()�����õ���ֵ����200 - 0 = 200�������ٺ�getMeasuredWidth()��ֵ��ͬ�ˡ���Ȼ����������ֲ�����measure()���̼�����Ľ����
ͨ��������ǲ��Ƽ���ôд�ġ�getHeight()��getMeasureHeight()����֮��Ĺ�ϵͬ�ϣ��Ͳ����ظ������ˡ�
����Ϊֹ�����ǰ���ͼ�������̵ĵڶ��׶�Ҳ�������ˡ�

��. onDraw()

measure��layout�Ĺ��̶������󣬽������ͽ��뵽draw�Ĺ����ˡ�ͬ����������������ܹ��жϳ���������������ؿ�ʼ����ͼ���л��ơ�
ViewRoot�еĴ�������ִ�в�������һ��Canvas����Ȼ�����View��draw()������ִ�о���Ļ��ƹ�����
draw()�����ڲ��Ļ��ƹ����ܹ����Է�Ϊ���������еڶ����͵��岽��һ������º����õ��������������ֻ�����򻯺�Ļ��ƹ��̡�����������ʾ��
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

���Կ�������һ���Ǵӵ�9�д��뿪ʼ�ģ���һ���������Ƕ���ͼ�ı������л��ơ�������ȵõ�һ��mBGDrawable����
Ȼ�����layout����ȷ������ͼλ�������ñ����Ļ�������֮���ٵ���Drawable��draw()��������ɱ����Ļ��ƹ�����
��ô���mBGDrawable�����Ǵ����������أ���ʵ������XML��ͨ��android:background�������õ�ͼƬ����ɫ����Ȼ��Ҳ�����ڴ�����ͨ��setBackgroundColor()��
setBackgroundResource()�ȷ������и�ֵ��
�������ĵ��������ڵ�34��ִ�еģ���һ���������Ƕ���ͼ�����ݽ��л��ơ����Կ���������ȥ������һ��onDraw()������
��ôonDraw()��������д��ʲô�����أ���ȥһ����ᷢ�֣�ԭ�����Ǹ��շ���������ʵҲ������⣬��Ϊÿ����ͼ�����ݲ��ֿ϶����Ǹ�����ͬ�ģ�
�ⲿ�ֵĹ��ܽ���������ȥʵ��Ҳ��������Ȼ�ġ�
���������֮������Ż�ִ�е��Ĳ�����һ���������ǶԵ�ǰ��ͼ����������ͼ���л��ơ��������ǰ����ͼû������ͼ����ôҲ�Ͳ���Ҫ���л����ˡ�
�����ᷢ��View�е�dispatchDraw()��������һ���շ�������ViewGroup��dispatchDraw()�����оͻ��о���Ļ��ƴ��롣
���϶�ִ�����ͻ���뵽��������Ҳ�����һ������һ���������Ƕ���ͼ�Ĺ��������л��ơ���ô����ܻ���֣���ǰ����ͼ�ֲ�һ����ListView����ScrollView��
ΪʲôҪ���ƹ������أ���ʵ������ButtonҲ�ã�TextViewҲ�ã��κ�һ����ͼ�����й������ģ�ֻ��һ����������Ƕ�û��������ʾ�������ѡ�
���ƹ������Ĵ����߼�Ҳ�Ƚϸ��ӣ�����Ͳ����������ˣ���Ϊ���ǵ��ص��ǵ��������̡�
ͨ���������̷��������Ŵ���Ѿ�֪����View�ǲ�������ǻ������ݲ��ֵģ������Ҫÿ����ͼ������Ҫչʾ�����������л��ơ�
�����ȥ�۲�TextView��ImageView�����Դ�룬��ᷢ�����Ƕ�����дonDraw()�������������������ִ�����൱���ٵĻ����߼���
���Ƶķ�ʽ��Ҫ�ǽ���Canvas����࣬������Ϊ�������뵽onDraw()�����У�����ÿ����ͼʹ�á�Canvas�������÷��ǳ��ḻ��
�������԰�������һ�黭�����������������Ķ�������ô���Ǿ�������һ�°ɡ�
������������ֻ�Ǵ���һ���ǳ��򵥵���ͼ��������Canvas��������һ�㶫��������������ʾ��
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

���Կ��������Ǵ�����һ���Զ����MyView�̳���View������MyView�Ĺ��캯���д�����һ��Paint����Paint������һ������һ����
�����Canvas�Ϳ��Խ��л����ˡ��������ǵĻ����߼��Ƚϼ򵥣���onDraw()���������ǰѻ������óɻ�ɫ��Ȼ�����Canvas��drawRect()��������һ�����Ρ�
Ȼ���ڰѻ������ó���ɫ����������һ�����ֵĴ�С��Ȼ�����drawText()����������һ�����֡�

����ô�򵥣�һ���Զ������ͼ���Ѿ�д���ˣ����ڿ�����XML�м��������ͼ��������ʾ��
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.example.viewtest.MyView 
        android:layout_width="200dp"
        android:layout_height="100dp"
        />

	</LinearLayout>

ͼ����ʾ������Ҳ����MyView�����ͼ�����ݲ����ˡ���������û��MyView���ñ�����������￴������View�Զ����Ƶı���Ч����
��Ȼ��Canvas���÷����кܶ�ܶ࣬�����Ҳ����ܰ�Canvas�������÷����оٳ�����ʣ�µľ�Ҫ���������ȥ�о���ѧϰ�ˡ�
����Ϊֹ�����ǰ���ͼ�������̵ĵ����׶�Ҳ�������ˡ�������ͼ�Ļ��ƹ��̾�ȫ�������ˣ��������ǲ��Ƕ�View��������������أ�
����Ȥ�����ѿ��Լ����Ķ� Android��ͼ״̬���ػ����̷���������һ���������˽�View(��) ��

���Ŵ����ƽʱʹ��View��ʱ�򶼻ᷢ��������״̬�ģ�����˵��һ����ť����ͨ״̬����һ��Ч�������ǵ���ָ���µ�ʱ��ͻ�������һ��Ч����
�����Ż���˲���һ�ֵ���˰�ť�ĸо�����Ȼ�ˣ�����Ч�����ż������е�Android����Ա��֪�������ʵ�֣��������Ǽ�Ȼ�������˽�View��
��ô��ȻҲӦ��֪���������ʵ��ԭ��Ӧ����ʲô���ģ��������������һ��̽��һ�°ɡ�

һ����ͼ״̬
��ͼ״̬������ǳ��࣬һ����ʮ�������ͣ������������������ֻ��ʹ�õ����еļ��֣������������Ҳ��ֻȥ������õļ�����ͼ״̬��
1. enabled
��ʾ��ǰ��ͼ�Ƿ���á����Ե���setEnable()�������ı���ͼ�Ŀ���״̬������true��ʾ���ã�����false��ʾ�����á�
����֮�������������ڣ������õ���ͼ���޷���ӦonTouch�¼��ġ�
2. focused
��ʾ��ǰ��ͼ�Ƿ��õ����㡣ͨ������������ַ�����������ͼ��ý��㣬��ͨ�����̵��������Ҽ��л���ͼ���Լ�����requestFocus()������
�����ڵ�Android�ֻ�������û�м����ˣ���˻�����ֻ����ʹ��requestFocus()����취������ͼ��ý����ˡ�
��requestFocus()����Ҳ���ܱ�֤һ����������ͼ��ý��㣬������һ������ֵ�ķ���ֵ���������true˵����ý���ɹ�������false˵����ý���ʧ�ܡ�
һ��ֻ����ͼ��focusable��focusable in touch modeͬʱ����������²��ܳɹ���ȡ���㣬����˵EditText��
3. window_focused
��ʾ��ǰ��ͼ�Ƿ������ڽ����Ĵ����У����ֵ��ϵͳ�Զ�������Ӧ�ó����ܽ��иı䡣
4. selected
��ʾ��ǰ��ͼ�Ƿ���ѡ��״̬��һ�����浱�п����ж����ͼ����ѡ��״̬������setSelected()�����ܹ��ı���ͼ��ѡ��״̬������true��ʾѡ�У�����false��ʾδѡ�С�
5. pressed
��ʾ��ǰ��ͼ�Ƿ��ڰ���״̬�����Ե���setPressed()����������һ״̬���иı䣬����true��ʾ���£�����false��ʾδ���¡�
ͨ����������״̬������ϵͳ�Զ���ֵ�ģ���������Ҳ�����Լ�����������������иı䡣

���ǿ�������Ŀ��drawableĿ¼�´���һ��selector�ļ�������������ÿ��״̬����ͼ��Ӧ�ı���ͼƬ�����紴��һ��compose_bg.xml�ļ����������д���´��룺

	<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:drawable="@drawable/compose_pressed" android:state_pressed="true"></item>
    <item android:drawable="@drawable/compose_pressed" android:state_focused="true"></item>
    <item android:drawable="@drawable/compose_normal"></item>

	</selector>

��δ���ͱ�ʾ������ͼ��������״̬��ʱ�����ʾcompose_normal���ű���ͼ������ͼ��õ�������߱����µ�ʱ�����ʾcompose_pressed���ű���ͼ��
�����������selector�ļ������ǾͿ����ڲ��ֻ������ʹ�����ˣ����罫������Ϊĳ����ť�ı���ͼ��������ʾ��

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

�������Ǿ���һ���ǳ��򵥵ķ���ʵ���˰�ť���µ�Ч�����������ı���ԭ�������������أ������Ҫ��Դ��Ĳ���Ͻ��з����ˡ�

��֪�����У�����һ���������˽�Viewϵ�е������Ѿ�д������ƪ�ˣ��ع�һ�£�����һ��ѧϰ��LayoutInflater��ԭ���������ͼ�Ļ������̡���ͼ��״̬���ػ��֪ʶ��
���ǰ�View�кܶ���Ҫ��֪ʶ�㶼�漰���ˡ�����㻹û�п�����ǰ��ļ�ƪ���£�������ȥ�Ķ�һ�£����˽�һЩԭ����Ķ�����

���˵Ҫ�����������ֵĻ����Զ���View��ʵ�ַ�ʽ��ſ��Է�Ϊ���֣��Ի�ؼ�����Ͽؼ����Լ��̳пؼ���
��ô�������Ǿ�������ѧϰһ�£�ÿ�ַ�ʽ�ֱ�������Զ���View�ġ�

һ���Ի�ؼ�
�Ի�ؼ�����˼���ǣ����View����չ�ֵ�����ȫ�����������Լ����Ƴ����ġ����ƵĴ�����д��onDraw()�����еģ�
���ⲿ�����������Ѿ��� Android��ͼ����������ȫ����������һ���������˽�View(��) ��ѧϰ���ˡ�
��������׼�����Զ���һ��������View�����View������Ӧ�û��ĵ���¼������Զ���¼һ������˶��ٴΡ��½�һ��CounterView�̳���View������������ʾ��

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
���Կ���������������CounterView�Ĺ��캯���г�ʼ����һЩ���ݣ��������View�ı���ע���˵���¼���������CounterView�������ʱ��
onClick()�����ͻ�õ����á���onClick()�����е��߼��͸��Ӽ��ˣ�ֻ�Ƕ�mCount�����������1��Ȼ�����invalidate()������
ͨ�� Android��ͼ״̬���ػ����̷���������һ���������˽�View(��) ��ƪ���µ�ѧϰ���Ƕ��Ѿ�֪��������invalidate()�����ᵼ����ͼ�����ػ棬
���onDraw()�������Ժ�ͽ���õ����á�
��ȻCounterView��һ���Ի���ͼ����ô����Ҫ���߼���Ȼ����д��onDraw()��������ˣ��������Ǿ�����ϸ��һ�¡�
���������ǽ�Paint��������Ϊ��ɫ��Ȼ�����Canvas��drawRect()����������һ�����Σ��������Ҳ�Ϳ��Ե�����CounterView�ı���ͼ�ɡ�
���Ž���������Ϊ��ɫ��׼���ڱ���������Ƶ�ǰ�ļ�����ע���������ǵ�����getTextBounds()��������ȡ�����ֵĿ�Ⱥ͸߶ȣ�
Ȼ�������drawText()����ȥ���л��ƾͿ����ˡ�
������һ���Զ����View���Ѿ�����ˣ�����Ŀǰ���CounterView�Ǿ߱��Զ��������ܵġ���ôʣ�µ����������������View�ڽ�������ʾ�����ˣ�
��ʵ��Ҳ�ǳ��򵥣�����ֻ��Ҫ��ʹ����ͨ�Ŀؼ�һ����ʹ��CounterView�Ϳ����ˡ������ڲ����ļ��м������´��룺

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.example.customview.CounterView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_centerInParent="true" />

	</RelativeLayout>

���Կ������������ǽ�CounterView������һ��RelativeLayout�У�Ȼ�������ʹ����ͨ�ؼ�����CounterViewָ���������ԣ�
����ͨ��layout_width��layout_height��ָ��CounterView�Ŀ�ߣ�ͨ��android:layout_centerInParent��ָ�����ڲ����������ʾ��
ֻ������Ҫע�⣬�Զ����View��ʹ�õ�ʱ��һ��Ҫд�������İ�������Ȼϵͳ���޷��ҵ����View��
���ˣ�������ô�򵥣����������ǿ�������һ�³��򣬲���ͣ�ص��CounterView��Ч������ͼ��ʾ��

��ô�����ǲ��Ǹо��Զ���ViewҲ������ʲô�߼��ļ������򵥼��д���Ϳ���ʵ���ˡ���Ȼ�ˣ����CounterView���ܷǳ���ª��ֻ��һ���������ܣ�
���ֻ�輸�д�����㹻�ˣ�������Ҫ���ƱȽϸ��ӵ�Viewʱ��������Ҫ�ܶ༼�ɵġ�

������Ͽؼ�
��Ͽؼ�����˼���ǣ����ǲ�����Ҫ�Լ�ȥ������ͼ����ʾ�����ݣ���ֻ����ϵͳԭ���Ŀؼ��ͺ��ˣ������ǿ��Խ�����ϵͳԭ���Ŀؼ���ϵ�һ��
�����������Ŀؼ��ͱ���Ϊ��Ͽؼ���
�ٸ�������˵�����������Ǹ��ܳ�������Ͽؼ����ܶ�����ͷ���������һ�����������������ϻ��и����ذ�ť�ͱ��⣬
�����ť��Ϳ��Է��ص���һ�����档��ô�������Ǿ�������ȥʵ������һ���������ؼ���
�½�һ��title.xml�����ļ�������������ʾ��

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

����������ļ��У��������ȶ�����һ��RelativeLayout��Ϊ�������֣�Ȼ������������ﶨ����һ��Button��һ��TextView��
Button���Ǳ������еķ��ذ�ť��TextView���Ǳ������е���ʾ�����֡�

����������һ��TitleView�̳���FrameLayout������������ʾ��

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

TitleView�еĴ���ǳ��򵥣���TitleView�Ĺ��������У����ǵ�����LayoutInflater��inflate()���������ظոն����title.xml���֣�
�ⲿ�����������Ѿ��� Android LayoutInflaterԭ�����������һ���������˽�View(һ) ��ƪ������ѧϰ���ˡ�
����������findViewById()������ȡ���˷��ذ�ť��ʵ����Ȼ��������onClick�¼��е���finish()�������رյ�ǰ��Activity��Ҳ���൱��ʵ�ַ��ع����ˡ�
���⣬Ϊ����TitleView�и�ǿ����չ�ԣ����ǻ��ṩ��setTitleText()��setLeftButtonText()��setLeftButtonListener()�ȷ�����
�ֱ��������ñ������ϵ����֡����ذ�ť�ϵ����֡��Լ����ذ�ť�ĵ���¼���
�������һ���Զ���ı�����������ˣ���ô�����ֵ��������������Զ���View�Ĳ��֣���ʵ��������������ͬ�ģ��ڲ����ļ���������´��룺

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
�����ͳɹ���һ���������ؼ����뵽�����ļ����ˣ�����һ�³���Ч������ͼ��ʾ��
���ڵ��һ��Back��ť���Ϳ��Թرյ�ǰ��Activity�ˡ��������Ҫ�޸ı���������ʾ�����ݣ����߷��ذ�ť��Ĭ���¼���
ֻ��Ҫ��Activity��ͨ��findViewById()�����õ�TitleView��ʵ����Ȼ�����setTitleText()��setLeftButtonText()��setLeftButtonListener()�ȷ����������þ�OK�ˡ�

�����̳пؼ�
�̳пؼ�����˼���ǣ����ǲ�����Ҫ�Լ���ͷȥʵ��һ���ؼ���ֻ��Ҫȥ�̳�һ�����еĿؼ���Ȼ��������ؼ�������һЩ�µĹ��ܣ��Ϳ����γ�һ���Զ���Ŀؼ��ˡ�
�����Զ���ؼ����ص���ǲ����ܹ��������ǵ����������Ӧ�Ĺ��ܣ������Ա���ԭ���ؼ������й��ܣ�
���� Android PowerImageViewʵ�֣����Բ��Ŷ�����ǿ��ImageView ��ƪ�����н��ܵ�PowerImageView����һ�����͵ļ̳пؼ���
Ϊ���ܹ������Ҷ������Զ���View��ʽ����⣬��������������дһ���µļ̳пؼ���ListView����ÿһ��Android����Ա��һ��ʹ�ù���
�������׼����ListView������չ��������ListView�ϻ����Ϳ�����ʾ��һ��ɾ����ť�������ť�ͻ�ɾ����Ӧ���ݵĹ��ܡ�
������Ҫ׼��һ��ɾ����ť�Ĳ��֣��½�delete_button.xml�ļ�������������ʾ��

<?xml version="1.0" encoding="utf-8"?>
<Button xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/delete_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:background="@drawable/delete_button" >

</Button>
��������ļ��ܼ򵥣�ֻ��һ����ť���ѣ��������Ǹ������ťָ����һ��ɾ������ͼ��
���Ŵ���MyListView�̳���ListView������������Զ����View�ˣ�����������ʾ��

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

���ڴ����߼��Ƚϼ򵥣��Ҿ�û�м�ע�͡�������MyListView�Ĺ��췽���д�����һ��GestureDetector��ʵ�����ڼ������ƣ�
Ȼ���MyListViewע����touch�����¼���Ȼ����onTouch()�����н����жϣ����ɾ����ť�Ѿ���ʾ�ˣ��ͽ����Ƴ�����
���ɾ����ťû����ʾ����ʹ��GestureDetector������ǰ���ơ�
����ָ����ʱ�������OnGestureListener��onDown()������������ͨ��pointToPosition()�������жϳ���ǰѡ�е���ListView����һ�С�
����ָ���ٻ���ʱ�������onFling()�������������ȥ����delete_button.xml������֣�Ȼ��ɾ����ť��ӵ���ǰѡ�е���һ��item�ϡ�
ע�⣬���ǻ���ɾ����ť�����һ������¼����������ɾ����ťʱ�ͻ�ص�onDeleteListener��onDelete()�������ڻص�������Ӧ��ȥ��������ɾ��������
���ˣ��Զ���View�Ĺ��ܵ��˾�����ˣ�������������Ҫ��һ����β���ʹ������Զ���View��������Ҫ����һ��ListView����Ĳ����ļ���
�½�my_list_view_item.xml������������ʾ��

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

Ȼ�󴴽�һ��������MyAdapter���������������ȥ����my_list_view_item���֣�����������ʾ��

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

������ͻ����Ѿ��깤�ˣ������ڳ�����������ļ���������MyListView����ؼ���������ʾ��
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

�����Activity�г�ʼ��MyListView�е����ݣ���������onDelete()������ɾ���߼�������������ʾ��

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

�����Ͱ��������ӵĴ��붼����ˣ���������һ�³��򣬻ῴ��MyListView������ListViewһ����������ʾ���е����ݣ�
���ǵ�������ָ��MyListView��ĳһ���Ͽ��ٻ���ʱ���ͻ���һ��ɾ����ť��ʾ����������ͼ��ʾ��