import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageShowcaseComponent } from './manage-showcase.component';

describe('ManageShowcaseComponent', () => {
  let component: ManageShowcaseComponent;
  let fixture: ComponentFixture<ManageShowcaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageShowcaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageShowcaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
