import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCEDADRECARGO, TCEDADRECARGO } from '../tcedadrecargo.model';

import { TCEDADRECARGOService } from './tcedadrecargo.service';

describe('Service Tests', () => {
  describe('TCEDADRECARGO Service', () => {
    let service: TCEDADRECARGOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCEDADRECARGO;
    let expectedResult: ITCEDADRECARGO | ITCEDADRECARGO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCEDADRECARGOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idEdadRecargo: 0,
        edadMin: 'AAAAAAA',
        edadMax: 'AAAAAAA',
        recargoAnterior: 0,
        recargoActual: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TCEDADRECARGO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCEDADRECARGO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCEDADRECARGO', () => {
        const returnedFromService = Object.assign(
          {
            idEdadRecargo: 1,
            edadMin: 'BBBBBB',
            edadMax: 'BBBBBB',
            recargoAnterior: 1,
            recargoActual: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCEDADRECARGO', () => {
        const patchObject = Object.assign(
          {
            edadMax: 'BBBBBB',
            recargoActual: 1,
          },
          new TCEDADRECARGO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCEDADRECARGO', () => {
        const returnedFromService = Object.assign(
          {
            idEdadRecargo: 1,
            edadMin: 'BBBBBB',
            edadMax: 'BBBBBB',
            recargoAnterior: 1,
            recargoActual: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TCEDADRECARGO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCEDADRECARGOToCollectionIfMissing', () => {
        it('should add a TCEDADRECARGO to an empty array', () => {
          const tCEDADRECARGO: ITCEDADRECARGO = { idEdadRecargo: 123 };
          expectedResult = service.addTCEDADRECARGOToCollectionIfMissing([], tCEDADRECARGO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCEDADRECARGO);
        });

        it('should not add a TCEDADRECARGO to an array that contains it', () => {
          const tCEDADRECARGO: ITCEDADRECARGO = { idEdadRecargo: 123 };
          const tCEDADRECARGOCollection: ITCEDADRECARGO[] = [
            {
              ...tCEDADRECARGO,
            },
            { idEdadRecargo: 456 },
          ];
          expectedResult = service.addTCEDADRECARGOToCollectionIfMissing(tCEDADRECARGOCollection, tCEDADRECARGO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCEDADRECARGO to an array that doesn't contain it", () => {
          const tCEDADRECARGO: ITCEDADRECARGO = { idEdadRecargo: 123 };
          const tCEDADRECARGOCollection: ITCEDADRECARGO[] = [{ idEdadRecargo: 456 }];
          expectedResult = service.addTCEDADRECARGOToCollectionIfMissing(tCEDADRECARGOCollection, tCEDADRECARGO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCEDADRECARGO);
        });

        it('should add only unique TCEDADRECARGO to an array', () => {
          const tCEDADRECARGOArray: ITCEDADRECARGO[] = [{ idEdadRecargo: 123 }, { idEdadRecargo: 456 }, { idEdadRecargo: 90453 }];
          const tCEDADRECARGOCollection: ITCEDADRECARGO[] = [{ idEdadRecargo: 123 }];
          expectedResult = service.addTCEDADRECARGOToCollectionIfMissing(tCEDADRECARGOCollection, ...tCEDADRECARGOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCEDADRECARGO: ITCEDADRECARGO = { idEdadRecargo: 123 };
          const tCEDADRECARGO2: ITCEDADRECARGO = { idEdadRecargo: 456 };
          expectedResult = service.addTCEDADRECARGOToCollectionIfMissing([], tCEDADRECARGO, tCEDADRECARGO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCEDADRECARGO);
          expect(expectedResult).toContain(tCEDADRECARGO2);
        });

        it('should accept null and undefined values', () => {
          const tCEDADRECARGO: ITCEDADRECARGO = { idEdadRecargo: 123 };
          expectedResult = service.addTCEDADRECARGOToCollectionIfMissing([], null, tCEDADRECARGO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCEDADRECARGO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
