jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCPRIMATARIFAService } from '../service/tcprimatarifa.service';
import { ITCPRIMATARIFA, TCPRIMATARIFA } from '../tcprimatarifa.model';

import { TCPRIMATARIFAUpdateComponent } from './tcprimatarifa-update.component';

describe('Component Tests', () => {
  describe('TCPRIMATARIFA Management Update Component', () => {
    let comp: TCPRIMATARIFAUpdateComponent;
    let fixture: ComponentFixture<TCPRIMATARIFAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCPRIMATARIFAService: TCPRIMATARIFAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCPRIMATARIFAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCPRIMATARIFAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCPRIMATARIFAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCPRIMATARIFAService = TestBed.inject(TCPRIMATARIFAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCPRIMATARIFA: ITCPRIMATARIFA = { idPrimaTarifa: 456 };

        activatedRoute.data = of({ tCPRIMATARIFA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCPRIMATARIFA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPRIMATARIFA = { idPrimaTarifa: 123 };
        spyOn(tCPRIMATARIFAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPRIMATARIFA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCPRIMATARIFA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCPRIMATARIFAService.update).toHaveBeenCalledWith(tCPRIMATARIFA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPRIMATARIFA = new TCPRIMATARIFA();
        spyOn(tCPRIMATARIFAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPRIMATARIFA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCPRIMATARIFA }));
        saveSubject.complete();

        // THEN
        expect(tCPRIMATARIFAService.create).toHaveBeenCalledWith(tCPRIMATARIFA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCPRIMATARIFA = { idPrimaTarifa: 123 };
        spyOn(tCPRIMATARIFAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCPRIMATARIFA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCPRIMATARIFAService.update).toHaveBeenCalledWith(tCPRIMATARIFA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
