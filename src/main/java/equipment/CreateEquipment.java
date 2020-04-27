package equipment;

import equipment.common.*;
import equipment.epic.*;
import equipment.legendary.*;
import equipment.rare.*;

public class CreateEquipment {
    // 获得普通装备的概率为55%
    private static final double getCommonEquipment = 1f;
    // 获得稀有装备的概率为20%
    private static final double getRareEquipment = 0.45f;
    // 获得史诗装备的概率为15%
    private static final double getEpicEquipment = 0.25f;
    // 获得传说装备的概率为10%
    private static final double getLegendaryEquipment = 0.1f;

    public static Equipment getRandomEquipment(){
        double random = Math.random();
        if (random < getLegendaryEquipment) {
            return getRandomLegendaryEquipment();
        }
        if (random > getLegendaryEquipment && random < getEpicEquipment) {
            return getRandomEpicEquipment();
        }
        if (random > getEpicEquipment && random < getRareEquipment) {
            return getRandomRareEquipment();
        }
        return getRandomCommonEquipment();


    }

    private static Equipment getRandomCommonEquipment() {
        int equipmentId = (int)(Math.random()*19);
        switch (equipmentId){
            case 1:
                return new Battery();
            case 2:
                return new BiologicalSample();
            case 3:
                return new BrokenRolex();
            case 4:
                return new Coupon();
            case 5:
                return new CurseBook();
            case 6:
                return new DemonHammer();
            case 7:
                return new DynamicAmpulla();
            case 8:
                return new KnifeStone();
            case 9:
                return new KnightGloves();
            case 10:
                return new MetroCard();
            case 11:
                return new PhilosopherStone();
            case 12:
                return new PoisonPill();
            case 13:
                return new Rind();
            case 14:
                return new SellManualOfGSW();
            case 15:
                return new SharpenedPencil();
            case 16:
                return new SmallShield();
            case 17:
                return new SnakeskinBag();
            case 18:
                return new SweaterWithThorns();
            default:
                return null;
        }
    }

    private static Equipment getRandomRareEquipment() {
        int equipmentId = (int)(Math.random()*17);
        switch (equipmentId){
            case 1:
                return new AlienwareOfWGL();
            case 2:
                return  new BlueTramcer();
            case 3:
                return new ComboStar();
            case 4:
                return new CursedBelt();
            case 5:
                return new CYXArtificialHair();
            case 6:
                return new DevilPrinceCloak();
            case 7:
                return new DimensionDoor();
            case 8:
                return new FireKernel();
            case 9:
                return new LifeKernel();
            case 10:
                return new MetalKernal();
            case 11:
                return new Popcorn();
            case 12:
                return new RubberBuffer();
            case 13:
                return new SeaKernel();
            case 14:
                return new SoilKernel();
            case 15:
                return new StickyNote();
            case 16:
                return new TheDragonTeethShield();
            default:
                return null;
        }
    }

    private static Equipment getRandomEpicEquipment() {
        int equipmentId = (int)(Math.random()*11);
        switch (equipmentId){
            case 1:
                return new ArmstrongGun();
            case 2:
                return new InvisibilityCloak();
            case 3:
                return new Kaleidoscope();
            case 4:
                return new LampOfAladdin();
            case 5:
                return new TheFlagOfTheFFRegiment();
            case 6:
                return new ThreeYearSimulationFiveYearCollegeEntranceExamination();
            case 7:
                return new WalletOfGSW();
            case 8:
                return new WeightBearing();
            case 9:
                return new WomenClothesOfCYX();
            case 10:
                return new WYRSteamAccount();
            default:
                return null;
        }
    }

    private static Equipment getRandomLegendaryEquipment() {
        int equipmentId = (int)(Math.random()*11);
        switch (equipmentId){
            case 1:
                return new BalancedLibra();
            case 2:
                return new ChunGeAmor();
            case 3:
                return new CurseChest();
            case 4:
                return new MedusaEyeMask();
            case 5:
                return new MerlinGown();
            case 6:
                return new MerlinShoes();
            case 7:
                return new MerlinWand();
            case 8:
                return new TheMythOfPride();
            case 9:
                return new ThePiccoloOfTheDarkElves();
            case 10:
                return new Tights();
            default:
                return null;
        }
    }

}
