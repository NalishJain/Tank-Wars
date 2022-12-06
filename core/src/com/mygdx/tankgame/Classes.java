package com.mygdx.tankgame;

interface Serializable{
    public void serialise();
    public void deserialise();
}

class GameObject implements Serializable{
    private Position position;
    private long serialUID;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public long getSerialUID() {
        return serialUID;
    }

    public void setSerialUID(long serialUID) {
        this.serialUID = serialUID;
    }

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }
}
class Player implements Serializable{
    private String name;
    private Tank playerTank;
    private Weapon[] curWeapons;
    private int power = 50;
    private int angle = 30;
    private boolean isDoubleDamage = false;
    private boolean isDoubleFuel = false;
    private boolean isSkippedChance = false;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }

    public Weapon[] getCurWeapons() {
        return curWeapons;
    }

    public void setCurWeapons(Weapon[] curWeapons) {
        this.curWeapons = curWeapons;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public boolean isDoubleDamage() {
        return isDoubleDamage;
    }

    public void setDoubleDamage(boolean doubleDamage) {
        isDoubleDamage = doubleDamage;
    }

    public boolean isDoubleFuel() {
        return isDoubleFuel;
    }

    public void setDoubleFuel(boolean doubleFuel) {
        isDoubleFuel = doubleFuel;
    }

    public boolean isSkippedChance() {
        return isSkippedChance;
    }

    public void setSkippedChance(boolean skippedChance) {
        isSkippedChance = skippedChance;
    }

    public void showWeaponTrail(int angle, int power){

    }
    public void showTimer(){}
    public Weapon selectWeapons(){

        return null;
    }
    public Position launchWeapon(int power, int angle, Weapon weapon){

        return null;
    }
    public Position launchSplitterWeapon(int power, int angle, Weapon weapon){

        return null;
    }
    public void move(){
        //use moveTank
    }
    public int causeDamage(Position weaponPosition){

        return 0;
    }

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }
}

class Weapon extends GameObject{
    private int damageRange;
    private int maxDamagePower;
    private Position weaponPosition;

    public int getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }

    public int getMaxDamagePower() {
        return maxDamagePower;
    }

    public void setMaxDamagePower(int maxDamagePower) {
        this.maxDamagePower = maxDamagePower;
    }

    public Position getWeaponPosition() {
        return weaponPosition;
    }

    public void setWeaponPosition(Position weaponPosition) {
        this.weaponPosition = weaponPosition;
    }

    public Position projectilePath(){
        return null;
    }

    public void shoot(int power , int angle){

    }
}

class Position implements Serializable{
    private int posX;
    private int posY;


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }
}

class SupplyDrop extends GameObject{
    private Position dropPosition;
    final String specialWeaponList[] = {"Earthquake", "Nuke", "HellFire", "Kamikaze"};
    final String powerUpList[] = {"Double Damage", "Med Kit", "Skip", "Double Fuel" };
    private boolean isPowerUp;
    private int id;

    SupplyDrop(){
        //will be generated by random
//id and power up will be set in the constructor
//constructor is called randomly with probability ⅕ on every turn
        // dropPosition = (x, MAX_HEIGHT)
    }


    public Position getDropPosition() {
        return dropPosition;
    }

    public void setDropPosition(Position dropPosition) {
        this.dropPosition = dropPosition;
    }

    public boolean isPowerUp() {
        return isPowerUp;
    }

    public void setPowerUp(boolean powerUp) {
        isPowerUp = powerUp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void dropPosition(){
        // changes dropPosition
    }
    public void onOpen(Player player){
       // activates player powerUp if powerUp, adds to WeaponList if Weapon
    }
}

class Tank extends GameObject {
    private int tankHp;
    private String tankName;//final
    private int tankAngle;
    private int fuel = 100;
    private Turret tankTurret;
    private boolean canMove = true;
    private Weapon[] weaponList;

    public int getTankHp() {
        return tankHp;
    }

    public void setTankHp(int tankHp) {
        this.tankHp = tankHp;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }


    public int getTankAngle() {
        return tankAngle;
    }

    public void setTankAngle(int tankAngle) {
        this.tankAngle = tankAngle;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public Turret getTankTurret() {
        return tankTurret;
    }

    public void setTankTurret(Turret tankTurret) {
        this.tankTurret = tankTurret;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Weapon[] getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(Weapon[] weaponList) {
        this.weaponList = weaponList;
    }
    class Turret{
        int turretAngle = 30;

        public int getTurretAngle(){

            return 0;
        }
        public void setTurretAngle(int angle){

        }
    }
    public boolean isDead(){

        return false;
    }
}

class Game implements Serializable {
    private Player player1;
    private Player player2;
    private GameObject[] gameObjects;
    private SupplyDrop[] supplyDrops;
    private Game[] savedGames;
    private int serialVersionUID;
    private int curPlayer = 1; //1,2


    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameObject[] getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(GameObject[] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public SupplyDrop[] getSupplyDrops() {
        return supplyDrops;
    }

    public void setSupplyDrops(SupplyDrop[] supplyDrops) {
        this.supplyDrops = supplyDrops;
    }

    public int getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(int serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public int getCurPlayer() {
        return curPlayer;
    }

    public void setCurPlayer(int curPlayer) {
        this.curPlayer = curPlayer;
    }

    public void pauseGame(){}


    public Game[] getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(Game[] savedGames) {
        this.savedGames = savedGames;
    }
    public boolean trySupplyDrop(){
        //will use random function to drop (1/5) uses function of supplyDrop class
        return false;
    }

    public void changeTurn(){

    }

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }
}

class Projectile extends GameObject{
    public Position projectilePath(Weapon weapon,  Position initPos,  int power,   int angle){

        return initPos;
    }

}
public class Classes {

}

