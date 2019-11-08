package com.cheise_proj.samplesqlitetestwork.di.module.local;

import android.content.Context;

import com.cheise_proj.data.model.ContactData;
import com.cheise_proj.data.model.UserData;
import com.cheise_proj.data.repository.LocalDataSource;
import com.cheise_proj.local_sqlite.db.AppLocalDatabase;
import com.cheise_proj.local_sqlite.db.dao.ContactDao;
import com.cheise_proj.local_sqlite.db.dao.ContactDaoImpl;
import com.cheise_proj.local_sqlite.db.dao.UserDao;
import com.cheise_proj.local_sqlite.db.dao.UserDaoImpl;
import com.cheise_proj.local_sqlite.mapper.base.Mapper;
import com.cheise_proj.local_sqlite.mapper.user.ContactDataLocalMapper;
import com.cheise_proj.local_sqlite.mapper.user.UserDataLocalMapper;
import com.cheise_proj.local_sqlite.model.ContactLocal;
import com.cheise_proj.local_sqlite.model.UserLocal;
import com.cheise_proj.local_sqlite.source.LocalDataSourceImpl;
import com.cheise_proj.samplesqlitetestwork.utils.Constants;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = {LocalModule.Binders.class})
public class LocalModule {
    @Module
    interface Binders {
        @Binds
        Mapper<UserData, UserLocal> bindsUserDataLocalMapper(UserDataLocalMapper userDataLocalMapper);

        @Binds
        UserDao bindsUserDaoImpl(UserDaoImpl userDao);

        @Binds
        LocalDataSource bindsLocalDataSourceImpl(LocalDataSourceImpl localDataSource);

        @Binds
        ContactDao bindContactDao(ContactDaoImpl contactDao);

        @Binds
        Mapper<ContactData, ContactLocal>  bindsContactDataLocalMapper(ContactDataLocalMapper contactDataLocalMapper);

    }

    @Provides
    AppLocalDatabase provideAppLocalDatabase(Context context) {
        return AppLocalDatabase.getInstance(context, Constants.LOCAL_DB_NAME);
    }
}
