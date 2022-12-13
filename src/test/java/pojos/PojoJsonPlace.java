package pojos;

public class PojoJsonPlace {
    // 1) Tum variable’lari  "private" olarak olusturalim
    // 2) Tum variable’lar icin getter() and setter() metodlari olusturalim
    // 3) Tum parametreleri iceren bir constructor olusturalim
    // 4) Default constructor (parametresiz) olusturalim
    // 5) toString() metodu olusturalim

     /* Request Body
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
    */

    // 1) Tum variable’lari  "private" olarak olusturalim
    private String title;
    private String body;
    private int userId;
    private int id;
    // 2) Tum variable’lar icin getter() and setter() metodlari olusturalim
    //cunku ilerleyen class şarda key lere yeni value atayabileyelim veya direkt degiştiremdne çağırabileyim.
    //code generate


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 3) Tum parametreleri iceren bir constructor olusturalim
    //code generate

    //constructor i bir atama işlemi yapılmak istendiginde çağırırız. ama to String methodunu ise bir donuş işlemi
    //yani sout işlemi durumunda kullanırız.
    public PojoJsonPlace(String title, String body, int userId, int id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }
    //bu class da bir constructor oluştugundan parametresiz constructor öldü.?
    //yeni bir tane default constrcutor oluşturmalıyız.


    // 4) Default constructor (parametresiz) olusturalim
    //select none ile oluşturduk.
    //ben yukarıda paramtreli bir constructor oluşturgumdan veya herhangi bir constructor oluşturdgumunda Java
    //hemen default olarak gorunmez constructor yani paramterseiz olan constructor i tamamen sildi
    //bir class in constrcutor i silinirse ben o class in variable larına veya ilgili ilgisiz methodlarina ulaşamam
    //dolayısıla hemen burada paramteresiz bir constructor oluşturmaliyim ki işim düştügünde
    //bu class taki bilgilere erişebileyim.

    public PojoJsonPlace() {

    }




    // 5) toString() metodu olusturalim
    //constructor i bir atama işlemi yapılmak istendiginde çağırırız. ama to String methodunu ise bir donuş işlemi
    //yani sout işlemi durumunda kullanırız.

    //yukarıdaki variable lari aldi, şuan onların referans degerleri var ama onları
    //toString methodunun içerisine sokarsam onları artık normal bir şekilde sorebilrim.



    @Override
    public String toString() {
        return "PojoJsonPlace{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }









}
