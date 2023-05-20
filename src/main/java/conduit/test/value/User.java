package conduit.test.value;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private String avatarUrl;

    private String username;

    private String bio;

    private String email;

    private String password;

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {}

        public User.Builder setUserAvatarUrl(String avatarUrl) {
            User.this.avatarUrl = avatarUrl;

            return this;
        }

        public User.Builder setUserName(String name) {
            User.this.username = name;

            return this;
        }

        public User.Builder setUserBio(String bio) {
            User.this.bio = bio;

            return this;
        }

        public User.Builder setUserEmail(String email) {
            User.this.email = email;

            return this;
        }

        public User.Builder setUserPassword(String password) {
            User.this.password = password;

            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
