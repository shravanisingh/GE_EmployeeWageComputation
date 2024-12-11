import java.util.ArrayList;

public class EmpWageBuilder {
    private ArrayList<CompanyEmpWage> companyEmpWages;

    public EmpWageBuilder() {
        this.companyEmpWages = new ArrayList<>();
    }

    public void addCompany(String companyName, int wagePerHour, int maxWorkingDays, int maxWorkingHours) {
        CompanyEmpWage companyEmpWage = new CompanyEmpWage(companyName, wagePerHour, maxWorkingDays, maxWorkingHours);
        companyEmpWages.add(companyEmpWage);
    }

    public void computeWages() {
        for (CompanyEmpWage companyEmpWage : companyEmpWages) {
            int totalWage = computeWageForCompany(companyEmpWage);
            companyEmpWage.setTotalWage(totalWage);
        }
    }

    private int computeWageForCompany(CompanyEmpWage companyEmpWage) {
        int totalWorkingDays = 0;
        int totalWorkingHours = 0;
        int totalWage = 0;

        System.out.println("\nCalculating wages for " + companyEmpWage.getCompanyName() + "...");

        while (totalWorkingDays < companyEmpWage.getMaxWorkingDays() && totalWorkingHours < companyEmpWage.getMaxWorkingHours()) {
            totalWorkingDays++;
            int dailyHours = getWorkingHours();
            totalWorkingHours += dailyHours;
            if (totalWorkingHours > companyEmpWage.getMaxWorkingHours()) {
                totalWorkingHours -= dailyHours;
                break;
            }
            int dailyWage = dailyHours * companyEmpWage.getWagePerHour();
            totalWage += dailyWage;

            System.out.println("Day " + totalWorkingDays + ": Worked " + dailyHours + " hours, Earned: " + dailyWage);
        }

        System.out.println("Total Wage for " + companyEmpWage.getCompanyName() + ": " + totalWage);
        System.out.println("Total Days Worked: " + totalWorkingDays + ", Total Hours Worked: " + totalWorkingHours);
        return totalWage;
    }

    private int getWorkingHours() {
        int empCheck = (int) (Math.random() * 3);
        switch (empCheck) {
            case 0: // Absent
                return 0;
            case 1: // Part-time
                return 4;
            case 2: // Full-time
                return 8;
            default:
                return 0;
        }
    }

    public void printCompanyWages() {
        System.out.println("\nSummary of Wages:");
        for (CompanyEmpWage companyEmpWage : companyEmpWages) {
            System.out.println(companyEmpWage);
        }
    }
}