package _Example.modele.deplacements;

import _Example.modele.plateau.Entite;
import _Example.modele.plateau.EntiteDynamique;

public class Gravite extends RealisateurDeDeplacement {
    @Override
    public boolean realiserDeplacement() {
        boolean ret = false;

        for (EntiteDynamique e : lstEntitesDynamiques) {
            Entite eBas = e.regarderDansLaDirection(Direction.bas);
            if (eBas == null || (eBas != null && !eBas.peutServirDeSupport())) {
                if (e.avancerDirectionChoisie(Direction.bas))
                    ret = true;
            }
        }

        return ret;
    }
}
