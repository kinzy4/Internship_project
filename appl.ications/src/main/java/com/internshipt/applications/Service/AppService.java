package com.internshipt.applications.Service;

import com.internshipt.applications.Repository.AppRepo;
import com.internshipt.entity.Model.Application;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    private final AppRepo appRepo;

    public AppService(AppRepo appRepo) {
        this.appRepo = appRepo;
    }
    public void submit_app(Application app) {
        appRepo.save(app);

    }
    public Application update_status(int applicationId, Application.Status newStatus) {
        Application application = appRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(newStatus);
        appRepo.save(application);

        return application;
    }

}
