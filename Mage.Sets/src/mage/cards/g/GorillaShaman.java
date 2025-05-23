package mage.cards.g;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.filter.predicate.Predicates;
import mage.target.TargetPermanent;
import mage.target.targetadjustment.XManaValueTargetAdjuster;

import java.util.UUID;

/**
 *
 * @author fireshoes
 */
public final class GorillaShaman extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("noncreature artifact with mana value X");

    static {
        filter.add(CardType.ARTIFACT.getPredicate());
        filter.add(Predicates.not(CardType.CREATURE.getPredicate()));
    }

    public GorillaShaman(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}");
        this.subtype.add(SubType.APE);
        this.subtype.add(SubType.SHAMAN);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // {X}{X}{1}: Destroy target noncreature artifact with converted mana cost X.
        Ability ability = new SimpleActivatedAbility(new DestroyTargetEffect(), new ManaCostsImpl<>("{X}{X}{1}"));
        ability.addTarget(new TargetPermanent(filter));
        ability.setTargetAdjuster(new XManaValueTargetAdjuster());
        this.addAbility(ability);
    }

    private GorillaShaman(final GorillaShaman card) {
        super(card);
    }

    @Override
    public GorillaShaman copy() {
        return new GorillaShaman(this);
    }
}
