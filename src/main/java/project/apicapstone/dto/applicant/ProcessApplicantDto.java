package project.apicapstone.dto.applicant;

public interface ProcessApplicantDto {
    String getApplicantId();

    String getStatus();

    String getResumeFile();

    String getScanData();

    //double getScore();

    JobPostingInfo getJobPosting();

    interface JobPostingInfo {
        String getJobPostingId();
    }
}
