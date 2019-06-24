public class player {
    public String name;
   public int age;
   public String email;
    public String nationality;
    public String club;

    public player(String name, int age, String email, String nationality, String club) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.nationality = nationality;
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
