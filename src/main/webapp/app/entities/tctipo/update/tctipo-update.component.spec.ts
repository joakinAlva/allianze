jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCTIPOService } from '../service/tctipo.service';
import { ITCTIPO, TCTIPO } from '../tctipo.model';

import { TCTIPOUpdateComponent } from './tctipo-update.component';

describe('Component Tests', () => {
  describe('TCTIPO Management Update Component', () => {
    let comp: TCTIPOUpdateComponent;
    let fixture: ComponentFixture<TCTIPOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCTIPOService: TCTIPOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCTIPOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCTIPOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCTIPOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCTIPOService = TestBed.inject(TCTIPOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCTIPO: ITCTIPO = { idTipo: 456 };

        activatedRoute.data = of({ tCTIPO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCTIPO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPO = { idTipo: 123 };
        spyOn(tCTIPOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCTIPO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCTIPOService.update).toHaveBeenCalledWith(tCTIPO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPO = new TCTIPO();
        spyOn(tCTIPOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCTIPO }));
        saveSubject.complete();

        // THEN
        expect(tCTIPOService.create).toHaveBeenCalledWith(tCTIPO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPO = { idTipo: 123 };
        spyOn(tCTIPOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCTIPOService.update).toHaveBeenCalledWith(tCTIPO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
