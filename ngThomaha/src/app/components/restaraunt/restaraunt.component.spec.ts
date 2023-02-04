import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestarauntComponent } from './restaraunt.component';

describe('RestarauntComponent', () => {
  let component: RestarauntComponent;
  let fixture: ComponentFixture<RestarauntComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestarauntComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestarauntComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
