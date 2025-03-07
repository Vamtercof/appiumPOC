package pageObjects;

public interface AppElements {
    String skipButtonId = "com.tubitv:id/top_skip_button_onboarding";
    String SkipButtonXpath = "//android.widget TextView[@resource-id='com.tubitvid/top_skip_button_onboarding']";

    String moviesButtonId = "com.tubitv:id/chip_movies";
    String moviesButtonIdXpath = "//android.widget.RadioButton[@resource-id'com.tubitv:id/chip_movies']";

    String familyMoviesXpath = "//android.widget.TextView[@resource-id='com.tubitv:id/view_title' and @text='Family Movies']";

    String firstAssetXpath = "(//android.widget.ImageView[@resource-id='com.tubitvid/video_poster_image_view'])[1]";

    String titleId = "com.tubitv:id/head_info_title";

}
