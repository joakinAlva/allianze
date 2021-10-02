jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCPRIMANETASDESCService } from '../service/tcprimanetasdesc.service';
import { ITCPRIMANETASDESC, TCPRIMANETASDESC } from '../tcprimanetasdesc.model';

import { TCPRIMANETASDESCUpdateComponent } from './tcprimanetasdesc-update.component';

describe('Component Tests', () => {
  describe('TCPRIMANETASDESC Management Update Component', () => {
    let comp: TCPRIMANETASDESCUpdateComponent;
    let fixture: ComponentFixture<TCPRIMANETASDESCUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCPRIMANETASDESCService: TCPRIMANETASDESCService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCPRIMANETASDESCUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCPRIMANETASDESCUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCPRIMANETASDESCUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCPRIMANETASDESCService = TestBed.inject(TCPRIMANETASDESCService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCPRIMANETASDESC: ITCPRIMANETASDESC = { idPrimaNetaSdesc: 456 };

        activatedRoute.data = of({ tCPRIMANETASDESC });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCPRIMANETASDESC));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPRIMANETASDESC = { idPrimaNetaSdesc: 123 };
        spyOn(tCPRIMANETASDESCService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPRIMANETASDESC });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCPRIMANETASDESC }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCPRIMANETASDESCService.update).toHaveBeenCalledWith(tCPRIMANETASDESC);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPRIMANETASDESC = new TCPRIMANETASDESC();
        spyOn(tCPRIMANETASDESCService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPRIMANETASDESC });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCPRIMANETASDESC }));
        saveSubject.complete();

        // THEN
        expect(tCPRIMANETASDESCService.create).toHaveBeenCalledWith(tCPRIMANETASDESC);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPRIMANETASDESC = { idPrimaNetaSdesc: 123 };
        spyOn(tCPRIMANETASDESCService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPRIMANETASDESC });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCPRIMANETASDESCService.update).toHaveBeenCalledWith(tCPRIMANETASDESC);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
