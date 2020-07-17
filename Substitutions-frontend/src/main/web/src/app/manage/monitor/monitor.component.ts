import { Component, OnInit } from '@angular/core';
import { Monitor } from './monitor.model'
import { MonitorService } from './monitor.service'
import { MenuItem } from 'primeng/api/menuitem';

@Component({
  selector: 'app-monitor',
  templateUrl: './monitor.component.html',
  styleUrls: ['./monitor.component.css']
})
export class MonitorComponent implements OnInit {

  displayDialog: boolean;

    monitor: Monitor = {};

    selectedMonitor: Monitor;

    newMonitor: boolean;

    monitors: Monitor[];

    cols: any[];

    items: MenuItem[];

    constructor(private monitorService: MonitorService) { }

    ngOnInit() {
        this.monitorService.getAllMonitors().then(monitors => {
            this.monitors = monitors;
            console.log(this.monitors);
        });

        this.cols = [
            { field: 'ipAddress', header: 'IP Address' },
            { field: 'name', header: 'Name' },
            { field: 'showGroup', header: 'Show Group' },
            { field: 'location', header: 'Location' },
            { field: 'status', header: 'Status' }
        ];


    }

    showDialogToAdd() {
        this.newMonitor = true;
        this.monitor = {};
        this.displayDialog = true;
    }

    save() {
        let monitors = [...this.monitors];
        if (this.newMonitor){
            this.monitorService.saveMonitor(this.monitor)
            monitors.push(this.monitor);
        } else
            monitors[this.monitors.indexOf(this.selectedMonitor)] = this.monitor;

        this.monitors = monitors;
        this.monitor = null;
        this.displayDialog = false;
    }

    delete() {
        let index = this.monitors.indexOf(this.selectedMonitor);
        this.monitors = this.monitors.filter((val, i) => i != index);
        this.monitor = null;
        this.displayDialog = false;
    }

    onRowSelect(event) {
        this.newMonitor = false;
        this.monitor = this.cloneCar(event.data);
        this.displayDialog = true;
    }

    cloneCar(c: Monitor): Monitor {
        let car = {};
        for (let prop in c) {
            car[prop] = c[prop];
        }
        return car;
    }

    monitorOff(){
        this.monitorService.monitorScreen(this.monitor, false);
    }

    monitorOn(){
        this.monitorService.monitorScreen(this.monitor, true);
    }
}
