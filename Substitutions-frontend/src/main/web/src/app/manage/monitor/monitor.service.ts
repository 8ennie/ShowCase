import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Monitor } from './monitor.model';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class MonitorService {

    ressorceUrl = environment.apiUrl + 'monitors';

    constructor(private http: HttpClient) { }

    getAllMonitors() {
        return this.http.get<any>(this.ressorceUrl).toPromise()
        .then(res => 
            <Monitor[]>res._embedded.monitors
        )
        .then(data => {
            return data;
        });
    }


    saveMonitor(monitor:Monitor){
        return this.http.post<any>(this.ressorceUrl, monitor).toPromise()
        .then(res => 
            <Monitor>res
        )
        .then(data => {
            return data;
        });
    }

    monitorScreen(monitor:Monitor, state: boolean){
        return this.http.post('http://' + monitor.ipAddress + '/screen/changeStatus?status=' + state, '').toPromise();
    }

}