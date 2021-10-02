jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCTIPOPRODUCTOService } from '../service/tctipoproducto.service';
import { ITCTIPOPRODUCTO, TCTIPOPRODUCTO } from '../tctipoproducto.model';

import { TCTIPOPRODUCTOUpdateComponent } from './tctipoproducto-update.component';

describe('Component Tests', () => {
  describe('TCTIPOPRODUCTO Management Update Component', () => {
    let comp: TCTIPOPRODUCTOUpdateComponent;
    let fixture: ComponentFixture<TCTIPOPRODUCTOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCTIPOPRODUCTOService: TCTIPOPRODUCTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCTIPOPRODUCTOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCTIPOPRODUCTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCTIPOPRODUCTOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCTIPOPRODUCTOService = TestBed.inject(TCTIPOPRODUCTOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCTIPOPRODUCTO: ITCTIPOPRODUCTO = { idTipoProducto: 456 };

        activatedRoute.data = of({ tCTIPOPRODUCTO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCTIPOPRODUCTO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPOPRODUCTO = { idTipoProducto: 123 };
        spyOn(tCTIPOPRODUCTOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPOPRODUCTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCTIPOPRODUCTO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCTIPOPRODUCTOService.update).toHaveBeenCalledWith(tCTIPOPRODUCTO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPOPRODUCTO = new TCTIPOPRODUCTO();
        spyOn(tCTIPOPRODUCTOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPOPRODUCTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCTIPOPRODUCTO }));
        saveSubject.complete();

        // THEN
        expect(tCTIPOPRODUCTOService.create).toHaveBeenCalledWith(tCTIPOPRODUCTO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCTIPOPRODUCTO = { idTipoProducto: 123 };
        spyOn(tCTIPOPRODUCTOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCTIPOPRODUCTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCTIPOPRODUCTOService.update).toHaveBeenCalledWith(tCTIPOPRODUCTO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
