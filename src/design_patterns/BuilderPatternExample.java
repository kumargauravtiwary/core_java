package design_patterns;

public class BuilderPatternExample {
	
	public static void main(String[] args) {
		User user = new User.Builder()
		        .name("Rahul")
		        .email("rahul@example.com")
		        .age(30)
		        .phone("9999999999")
		        .build();
	}
}
 class User {

    private final String name;
    private final String email;
    private final int age;
    private final String phone;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
    }

    public static class Builder {

        private String name;
        private String email;
        private int age;
        private String phone;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
