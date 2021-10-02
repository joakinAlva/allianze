import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCRANGOPRIMA, TCRANGOPRIMA } from '../tcrangoprima.model';

import { TCRANGOPRIMAService } from './tcrangoprima.service';

describe('Service Tests', () => {
  describe('TCRANGOPRIMA Service', () => {
    let service: TCRANGOPRIMAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCRANGOPRIMA;
    let expectedResult: ITCRANGOPRIMA | ITCRANGOPRIMA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCRANGOPRIMAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idRangoPrima: 0,
        valorMin: 0,
        valorMax: 0,
        dividendos: 0,
        comision: 0,
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

      it('should create a TCRANGOPRIMA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCRANGOPRIMA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCRANGOPRIMA', () => {
        const returnedFromService = Object.assign(
          {
            idRangoPrima: 1,
            valorMin: 1,
            valorMax: 1,
            dividendos: 1,
            comision: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCRANGOPRIMA', () => {
        const patchObject = Object.assign(
          {
            valorMin: 1,
            dividendos: 1,
          },
          new TCRANGOPRIMA()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCRANGOPRIMA', () => {
        const returnedFromService = Object.assign(
          {
            idRangoPrima: 1,
            valorMin: 1,
            valorMax: 1,
            dividendos: 1,
            comision: 1,
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

      it('should delete a TCRANGOPRIMA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCRANGOPRIMAToCollectionIfMissing', () => {
        it('should add a TCRANGOPRIMA to an empty array', () => {
          const tCRANGOPRIMA: ITCRANGOPRIMA = { idRangoPrima: 123 };
          expectedResult = service.addTCRANGOPRIMAToCollectionIfMissing([], tCRANGOPRIMA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCRANGOPRIMA);
        });

        it('should not add a TCRANGOPRIMA to an array that contains it', () => {
          const tCRANGOPRIMA: ITCRANGOPRIMA = { idRangoPrima: 123 };
          const tCRANGOPRIMACollection: ITCRANGOPRIMA[] = [
            {
              ...tCRANGOPRIMA,
            },
            { idRangoPrima: 456 },
          ];
          expectedResult = service.addTCRANGOPRIMAToCollectionIfMissing(tCRANGOPRIMACollection, tCRANGOPRIMA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCRANGOPRIMA to an array that doesn't contain it", () => {
          const tCRANGOPRIMA: ITCRANGOPRIMA = { idRangoPrima: 123 };
          const tCRANGOPRIMACollection: ITCRANGOPRIMA[] = [{ idRangoPrima: 456 }];
          expectedResult = service.addTCRANGOPRIMAToCollectionIfMissing(tCRANGOPRIMACollection, tCRANGOPRIMA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCRANGOPRIMA);
        });

        it('should add only unique TCRANGOPRIMA to an array', () => {
          const tCRANGOPRIMAArray: ITCRANGOPRIMA[] = [{ idRangoPrima: 123 }, { idRangoPrima: 456 }, { idRangoPrima: 12017 }];
          const tCRANGOPRIMACollection: ITCRANGOPRIMA[] = [{ idRangoPrima: 123 }];
          expectedResult = service.addTCRANGOPRIMAToCollectionIfMissing(tCRANGOPRIMACollection, ...tCRANGOPRIMAArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCRANGOPRIMA: ITCRANGOPRIMA = { idRangoPrima: 123 };
          const tCRANGOPRIMA2: ITCRANGOPRIMA = { idRangoPrima: 456 };
          expectedResult = service.addTCRANGOPRIMAToCollectionIfMissing([], tCRANGOPRIMA, tCRANGOPRIMA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCRANGOPRIMA);
          expect(expectedResult).toContain(tCRANGOPRIMA2);
        });

        it('should accept null and undefined values', () => {
          const tCRANGOPRIMA: ITCRANGOPRIMA = { idRangoPrima: 123 };
          expectedResult = service.addTCRANGOPRIMAToCollectionIfMissing([], null, tCRANGOPRIMA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCRANGOPRIMA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
