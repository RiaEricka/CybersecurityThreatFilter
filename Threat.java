class Threat {
    
    String Country;
    int Year;
    String AttackType;
    String TargetIndustry;
    double LossInMillions;
    int AffectedUsers;
    String AttackSource;
    String VulnerabilityType;
    String DefenceMechanism;
    int ResoulutionType;

    Threat(String country, int year, String attacktype, String targetindustry, double lossinmillions, int affectedusers, String attacksource, String vulnerabilitytype, String defencemechanism, int resolutiontype) {
        Country = country;
        Year = year;
        AttackType = attacktype;
        TargetIndustry = targetindustry;
        LossInMillions = lossinmillions;
        AffectedUsers = affectedusers;
        AttackSource = attacksource;
        VulnerabilityType = vulnerabilitytype;
        DefenceMechanism = defencemechanism;
        ResoulutionType = resolutiontype;
    }

    public String getCountry() {
        return Country;
    }

    public int getYear() {
        return Year;
    }

    public String getAttackType() {
        return AttackType;
    }

    public String getTargetIndustry() {
        return TargetIndustry;
    }

    public double getLossInMillions() {
        return LossInMillions;
    }

    public int getAffectedUsers() {
        return AffectedUsers;
    }

    public String getAttackSource() {
        return AttackSource;
    }

    public String getVulnerabilityType() {
        return VulnerabilityType;
    }

    public String getDefenceMechanism() {
        return DefenceMechanism;
    }

    public int getResoulutionType() {
        return ResoulutionType;
    }
}

