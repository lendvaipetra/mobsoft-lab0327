package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

/**
 * Created by Petra on 2017. 04. 30..
 */

public class GetRecipesCountEvent {
        private long count;
        private Throwable throwable;

        public GetRecipesCountEvent() {
        }
        public long getRecipesCount() {
            return count;
        }

        public void setRecipesCount(long count) {
            this.count = count;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }
}
