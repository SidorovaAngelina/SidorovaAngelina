package task;

public class ArmorDestroyer extends BattleUnitBase{
    public ArmorDestroyer(String name, int maxHealth, int baseStrength, int maxArmor){
        super(name, maxHealth, baseStrength, maxArmor);
    }

    /*выбирает живого противника с максимальной броней.
            *          Если противник в броне, наносит урон броне, равный удвоенной силе.
            *          Иначе наносит урон здоровью, равный четверти силы. */

    public void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam) {
        int armorMax=0;
        BattleUnit enemy = enemyTeam[0];
        for(int i=0; i<enemyTeam.length; i++){
            if(enemyTeam[i].maxArmor()>armorMax && enemyTeam[i].health()>0){
                armorMax=enemyTeam[i].maxArmor();
                enemy=enemyTeam[i];
            }
        }
        if(enemy.armor()>0){
            enemy.damageArmor(strength()*2);
        }
        else{
            enemy.takeDamage(strength()/4);
        }
    }

    /*если противник в броне,
         *              наносит повреждения по здоровью, равные четверти силы,
         *              и повреждения по броне равные силе, иначе
         *              наносит повреждения здоровью, равные половине силы.*/


    public void attack(BattleUnit other){
        if(other.armor()>0){
            if(strength()/4>0){
                other.takeDamage(strength()/4);
            }
            else{
                other.takeDamage(1);
            }
            if(strength()>0){
                other.damageArmor(strength());
            }
            else{
                other.takeDamage(1);
            }
        }
        else{
            other.takeDamage(strength()/2);
        }
    }
}