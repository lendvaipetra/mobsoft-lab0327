package hu.bme.aut.mobsoft.lab.mobsoft.repository;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Petra on 2017. 04. 17..
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public Repository provideRepository() {
        return new SugarOrmRepository();
    }
}
