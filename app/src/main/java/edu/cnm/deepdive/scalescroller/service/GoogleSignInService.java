package edu.cnm.deepdive.scalescroller.service;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import io.reactivex.Single;

/**
 * The GoogleSignInService class provides methods that allow the app to use Google Sign In to get
 * user information and to associate users with players in the database.
 */
public class GoogleSignInService {

  private static Application context;
  private final GoogleSignInClient client;
  private GoogleSignInAccount account;

  private GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
        .build();
    client = GoogleSignIn.getClient(context, options);
  }

  /**
   * Sets the application context for the class.
   *
   * @param context The application context.
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * Returns an instance of the singleton GoogleSignInService.
   *
   * @return An instance of GoogleSignInService.
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Returns the current Google Sign In account.
   *
   * @return The current account.
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Refreshes the signin with Google.
   *
   * @return A {@code Single} of a GoogleSignInAccount.
   */
  public Single<GoogleSignInAccount> refresh() {
    return Single.create((emitter) -> {
          client.silentSignIn()
              .addOnSuccessListener(this::setAccount)
              .addOnSuccessListener(emitter::onSuccess)
              .addOnFailureListener(emitter::onError);
        }
    );
  }

  /**
   * Allows the activity to start a signin request.
   *
   * @param activity    The LoginActivity.
   * @param requestCode A code that indicates a type of request.
   */
  public void startSignIn(Activity activity, int requestCode) {
    account = null;
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  /**
   * Completes the signin process and sets the account.
   *
   * @param data An intent.
   * @return A {@code Task} containing a {@code GoogleSignInAccount}.
   */
  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      setAccount(task.getResult(ApiException.class));
    } catch (ApiException e) {
      // Exception will be passed automatically to onFailureListener.
    }
    return task;
  }

  /**
   * Signs the user out. If the signout is unsucessful, the account is still set to null.
   *
   * @return A {@code Task} containing {@code Void}.
   */
  public Task<Void> signOut() {
    return client.signOut()
        .addOnCompleteListener((ignored) -> setAccount(null));
  }

  private void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();
  }

}
