package com.imobile3.groovypayments.data;

import android.content.Context;
import android.os.AsyncTask;

import com.imobile3.groovypayments.MainApplication;
import com.imobile3.groovypayments.data.entities.CartEntity;
import com.imobile3.groovypayments.data.entities.CartProductEntity;
import com.imobile3.groovypayments.data.entities.ProductEntity;
import com.imobile3.groovypayments.data.entities.ProductTaxJunctionEntity;
import com.imobile3.groovypayments.data.entities.TaxEntity;
import com.imobile3.groovypayments.data.entities.UserEntity;
import com.imobile3.groovypayments.utils.MockDataHelper;

import androidx.annotation.NonNull;


public final class GroovyDemoManager {

    private static final String TAG = GroovyDemoManager.class.getSimpleName();

    //region Singleton Implementation

    private static GroovyDemoManager sInstance;

    private GroovyDemoManager() {
    }

    @NonNull
    public static synchronized GroovyDemoManager getInstance() {
        if (sInstance == null) {
            sInstance = new GroovyDemoManager();
        }
        return sInstance;
    }

    //endregion

    public interface ResetDatabaseCallback {

        void onDatabaseReset();
    }

    /**
     * Delete the current database instance (potentially dangerous operation!)
     * and populate a new instance with fresh demo data.
     */
    public void resetDatabase(
            @NonNull final ResetDatabaseCallback callback) {
        new ResetDatabaseTask(MainApplication.getInstance(), callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class ResetDatabaseTask extends AsyncTask<Void, Void, Void> {

        @NonNull
        private final Context mContext;
        @NonNull
        private final ResetDatabaseCallback mCallback;

        private ResetDatabaseTask(
                @NonNull final Context context,
                @NonNull final ResetDatabaseCallback callback) {
            mContext = context;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Blow away any existing database instance.
            DatabaseHelper.getInstance().eraseDatabase(mContext);

            // Initialize a new database instance.
            DatabaseHelper.getInstance().init(mContext);

            // Datbase convenience variable.
            GroovyDatabase database = DatabaseHelper.getInstance().getDatabase();

            // Insert entities into database instance.
            database.getProductDao().insertProducts(
                    MockDataHelper.getInstance()
                            .getMockProducts().toArray(new ProductEntity[0]));

            // Insert MOCK entities into database instance.
            database.getTaxDao()
                    .insertTaxes(
                            MockDataHelper.getInstance()
                                    .getMockTaxEntity()
                                    .toArray(new TaxEntity[0]));

            database.getProductTaxJunctionDao()
                    .insertProductTaxJunctions(
                            MockDataHelper.getInstance()
                                    .getMockProductTaxJunctionEntity()
                                    .toArray(new ProductTaxJunctionEntity[0]));

            database.getCartDao()
                    .insertCarts(
                            MockDataHelper.getInstance()
                                    .getMockCartEntity()
                                    .toArray(new CartEntity[0]));

            database.getCartProductDao()
                    .insertCartProducts(
                            MockDataHelper.getInstance()
                                    .getMockCartProductEntity()
                                    .toArray(new CartProductEntity[0]));

            database.getUserDao()
                    .insertUsers(
                            MockDataHelper.getInstance()
                                    .getMockUserEntity()
                                    .toArray(new UserEntity[0]));

            // All done!
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mCallback.onDatabaseReset();
        }
    }
}
