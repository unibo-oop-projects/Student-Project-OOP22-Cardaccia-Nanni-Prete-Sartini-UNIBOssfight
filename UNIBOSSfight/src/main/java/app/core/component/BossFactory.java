package app.core.component;

import app.core.entity.Boss;

public interface BossFactory {

    Boss firstBoss(final Transform startingPos);

}
