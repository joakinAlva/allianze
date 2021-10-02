jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCRECARGOPAGOFRACCIONADOService } from '../service/tcrecargopagofraccionado.service';
import { ITCRECARGOPAGOFRACCIONADO, TCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';

import { TCRECARGOPAGOFRACCIONADOUpdateComponent } from './tcrecargopagofraccionado-update.component';

describe('Component Tests', () => {
  describe('TCRECARGOPAGOFRACCIONADO Management Update Component', () => {
    let comp: TCRECARGOPAGOFRACCIONADOUpdateComponent;
    let fixture: ComponentFixture<TCRECARGOPAGOFRACCIONADOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCRECARGOPAGOFRACCIONADOService: TCRECARGOPAGOFRACCIONADOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCRECARGOPAGOFRACCIONADOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCRECARGOPAGOFRACCIONADOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCRECARGOPAGOFRACCIONADOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCRECARGOPAGOFRACCIONADOService = TestBed.inject(TCRECARGOPAGOFRACCIONADOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 456 };

        activatedRoute.data = of({ tCRECARGOPAGOFRACCIONADO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCRECARGOPAGOFRACCIONADO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 123 };
        spyOn(tCRECARGOPAGOFRACCIONADOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCRECARGOPAGOFRACCIONADO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCRECARGOPAGOFRACCIONADO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCRECARGOPAGOFRACCIONADOService.update).toHaveBeenCalledWith(tCRECARGOPAGOFRACCIONADO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCRECARGOPAGOFRACCIONADO = new TCRECARGOPAGOFRACCIONADO();
        spyOn(tCRECARGOPAGOFRACCIONADOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCRECARGOPAGOFRACCIONADO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCRECARGOPAGOFRACCIONADO }));
        saveSubject.complete();

        // THEN
        expect(tCRECARGOPAGOFRACCIONADOService.create).toHaveBeenCalledWith(tCRECARGOPAGOFRACCIONADO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCRECARGOPAGOFRACCIONADO = { idRecargoPagoFraccionado: 123 };
        spyOn(tCRECARGOPAGOFRACCIONADOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCRECARGOPAGOFRACCIONADO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCRECARGOPAGOFRACCIONADOService.update).toHaveBeenCalledWith(tCRECARGOPAGOFRACCIONADO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
