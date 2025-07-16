package dsm.hackaton._8.domain.event;

import dsm.hackaton._8.domain.law.domain.Law;
import lombok.Getter;

@Getter
public class LawUpdateEvent {

    private final Law law;

    public LawUpdateEvent(Law law) {
        this.law = law;
    }
}
