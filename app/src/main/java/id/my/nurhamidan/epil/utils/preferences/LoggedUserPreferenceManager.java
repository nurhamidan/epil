package id.my.nurhamidan.epil.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import id.my.nurhamidan.epil.models.User;

public class LoggedUserPreferenceManager {
    private static final String PREFERENCE_NAME = "id.my.nurhamidan.epil";
    private static final String USER_ID_KEY = "userIdKey";
    private static final String USER_NAME_KEY = "userNameKey";
    private static final String USER_EMAIL_KEY = "userEmailKey";
    private static final String USER_USERNAME_KEY = "userUsernameKey";
    private static final String USER_PASSWORD_KEY = "userPasswordKey";
    private Context context;

    public LoggedUserPreferenceManager(Context context) {
        this.context = context;
    }

    public LoggedUserPreferenceManager(Context context, User user) {
        this.context = context;
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID_KEY, user.getId());
        editor.putString(USER_NAME_KEY, user.getName());
        editor.putString(USER_EMAIL_KEY, user.getEmail());
        editor.putString(USER_USERNAME_KEY, user.getUsername());
        editor.putString(USER_PASSWORD_KEY, user.getPassword());
        editor.apply();
    }

    public User getUser() {
        User user = new User();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        user.setId(sharedPreferences.getInt(USER_ID_KEY, -1));
        user.setName(sharedPreferences.getString(USER_NAME_KEY, null));
        user.setEmail(sharedPreferences.getString(USER_EMAIL_KEY, null));
        user.setUsername(sharedPreferences.getString(USER_USERNAME_KEY, null));
        user.setPassword(sharedPreferences.getString(USER_PASSWORD_KEY, null));
        return user;
    }

    public void setOutUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
