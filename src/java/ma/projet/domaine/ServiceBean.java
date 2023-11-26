/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employee;
import ma.projet.beans.Service;
import ma.projet.service.EmployeeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "serviceBean")
public class ServiceBean {

    private Employee employe;
    private Service service;
    private ServiceService serviceService;
    private List<Service> services;
    private List<Employee> employes;
    private EmployeeService employeService;
    private static ChartModel barModel;

    public ServiceBean() {
        service = new Service();
        serviceService = new ServiceService();
        employe = new Employee();
        employeService = new EmployeeService();
    }

    public void setEmployees(List<Employee> employes) {
        this.employes = employes;
    }

    public List<Service> getServices() {
        if (services == null) {
            services = serviceService.getAll();
        }
        return services;
    }

    public List<Employee> getEmployees() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String onDeleteAction() {

        serviceService.delete(serviceService.getById(service.getId()));
        return null;
    }

    public String onCreateAction() {
        serviceService.create(service);
        service = new Service();
        return null;
    }

    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        serviceService.update(service);
    }

    public void onCancel(RowEditEvent event) {
    }

    public void onEditm(RowEditEvent event) {
        employe = (Employee) event.getObject();
        Service service = serviceService.getById((int) this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }

    public String onDeleteActionm() {

        employeService.delete(employeService.getById((int) employe.getId()));
        return null;
    }

    public List<Employee> serviceLoad() {
        employes = new ArrayList<>();
        for (Employee m : employeService.getAll()) {
            if (m.getService().equals(service)) {
                employes.add(m);
            }
        }
        return employes;
    }

    public void onCancelm(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

       
  public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries services = new ChartSeries();
        services.setLabel("Services");
        model.setAnimate(true);
        for (Service s : serviceService.getAll()) {
            services.set(s.getNom(), s.getEmployees().size());
        }
        model.addSeries(services);

        return model;
    }
}
