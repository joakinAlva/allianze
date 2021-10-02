jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCREGIONALService } from '../service/tcregional.service';
import { ITCREGIONAL, TCREGIONAL } from '../tcregional.model';

import { TCREGIONALUpdateComponent } from './tcregional-update.component';

describe('Component Tests', () => {
  describe('TCREGIONAL Management Update Component', () => {
    let comp: TCREGIONALUpdateComponent;
    let fixture: ComponentFixture<TCREGIONALUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCREGIONALService: TCREGIONALService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCREGIONALUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCREGIONALUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCREGIONALUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCREGIONALService = TestBed.inject(TCREGIONALService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCREGIONAL: ITCREGIONAL = { idRegional: 456 };

        activatedRoute.data = of({ tCREGIONAL });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCREGIONAL));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCREGIONAL = { idRegional: 123 };
        spyOn(tCREGIONALService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCREGIONAL });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCREGIONAL }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCREGIONALService.update).toHaveBeenCalledWith(tCREGIONAL);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCREGIONAL = new TCREGIONAL();
        spyOn(tCREGIONALService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCREGIONAL });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCREGIONAL }));
        saveSubject.complete();

        // THEN
        expect(tCREGIONALService.create).toHaveBeenCalledWith(tCREGIONAL);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCREGIONAL = { idRegional: 123 };
        spyOn(tCREGIONALService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCREGIONAL });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCREGIONALService.update).toHaveBeenCalledWith(tCREGIONAL);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
