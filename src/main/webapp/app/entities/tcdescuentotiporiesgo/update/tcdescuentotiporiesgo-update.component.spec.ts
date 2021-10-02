jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCDESCUENTOTIPORIESGOService } from '../service/tcdescuentotiporiesgo.service';
import { ITCDESCUENTOTIPORIESGO, TCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';

import { TCDESCUENTOTIPORIESGOUpdateComponent } from './tcdescuentotiporiesgo-update.component';

describe('Component Tests', () => {
  describe('TCDESCUENTOTIPORIESGO Management Update Component', () => {
    let comp: TCDESCUENTOTIPORIESGOUpdateComponent;
    let fixture: ComponentFixture<TCDESCUENTOTIPORIESGOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCDESCUENTOTIPORIESGOService: TCDESCUENTOTIPORIESGOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCDESCUENTOTIPORIESGOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCDESCUENTOTIPORIESGOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCDESCUENTOTIPORIESGOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCDESCUENTOTIPORIESGOService = TestBed.inject(TCDESCUENTOTIPORIESGOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 456 };

        activatedRoute.data = of({ tCDESCUENTOTIPORIESGO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCDESCUENTOTIPORIESGO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 123 };
        spyOn(tCDESCUENTOTIPORIESGOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCDESCUENTOTIPORIESGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCDESCUENTOTIPORIESGO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCDESCUENTOTIPORIESGOService.update).toHaveBeenCalledWith(tCDESCUENTOTIPORIESGO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCDESCUENTOTIPORIESGO = new TCDESCUENTOTIPORIESGO();
        spyOn(tCDESCUENTOTIPORIESGOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCDESCUENTOTIPORIESGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCDESCUENTOTIPORIESGO }));
        saveSubject.complete();

        // THEN
        expect(tCDESCUENTOTIPORIESGOService.create).toHaveBeenCalledWith(tCDESCUENTOTIPORIESGO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 123 };
        spyOn(tCDESCUENTOTIPORIESGOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCDESCUENTOTIPORIESGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCDESCUENTOTIPORIESGOService.update).toHaveBeenCalledWith(tCDESCUENTOTIPORIESGO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
