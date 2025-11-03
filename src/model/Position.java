package model;

public enum Position {
    PREZES(25000, 1),
    VICEPREZES(18000, 2),
    MANAGER(12000, 3),
    PROGRAMISTA(8000, 4),
    STAZYSTA(3000, 5);

    private double baseSalary;
    private int level;

    Position(double baseSalary, int level) {
        this.baseSalary = baseSalary;
        this.level = level;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public int getLevel() {
        return level;
    }

    public double baseSalary(){
        return baseSalary;
    }
}

