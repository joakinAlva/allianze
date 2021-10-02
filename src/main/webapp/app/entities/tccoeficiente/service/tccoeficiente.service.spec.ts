import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCOEFICIENTE, TCCOEFICIENTE } from '../tccoeficiente.model';

import { TCCOEFICIENTEService } from './tccoeficiente.service';

describe('Service Tests', () => {
  describe('TCCOEFICIENTE Service', () => {
    let service: TCCOEFICIENTEService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCOEFICIENTE;
    let expectedResult: ITCCOEFICIENTE | ITCCOEFICIENTE[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCOEFICIENTEService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCoeficiente: 0,
        coeficiente: 0,
        intervaloMin: 0,
        intervaloMax: 0,
        descuentoExtra: 0,
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

      it('should create a TCCOEFICIENTE', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCOEFICIENTE()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCOEFICIENTE', () => {
        const returnedFromService = Object.assign(
          {
            idCoeficiente: 1,
            coeficiente: 1,
            intervaloMin: 1,
            intervaloMax: 1,
            descuentoExtra: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCOEFICIENTE', () => {
        const patchObject = Object.assign(
          {
            coeficiente: 1,
            intervaloMax: 1,
          },
          new TCCOEFICIENTE()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCOEFICIENTE', () => {
        const returnedFromService = Object.assign(
          {
            idCoeficiente: 1,
            coeficiente: 1,
            intervaloMin: 1,
            intervaloMax: 1,
            descuentoExtra: 1,
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

      it('should delete a TCCOEFICIENTE', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCOEFICIENTEToCollectionIfMissing', () => {
        it('should add a TCCOEFICIENTE to an empty array', () => {
          const tCCOEFICIENTE: ITCCOEFICIENTE = { idCoeficiente: 123 };
          expectedResult = service.addTCCOEFICIENTEToCollectionIfMissing([], tCCOEFICIENTE);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOEFICIENTE);
        });

        it('should not add a TCCOEFICIENTE to an array that contains it', () => {
          const tCCOEFICIENTE: ITCCOEFICIENTE = { idCoeficiente: 123 };
          const tCCOEFICIENTECollection: ITCCOEFICIENTE[] = [
            {
              ...tCCOEFICIENTE,
            },
            { idCoeficiente: 456 },
          ];
          expectedResult = service.addTCCOEFICIENTEToCollectionIfMissing(tCCOEFICIENTECollection, tCCOEFICIENTE);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCOEFICIENTE to an array that doesn't contain it", () => {
          const tCCOEFICIENTE: ITCCOEFICIENTE = { idCoeficiente: 123 };
          const tCCOEFICIENTECollection: ITCCOEFICIENTE[] = [{ idCoeficiente: 456 }];
          expectedResult = service.addTCCOEFICIENTEToCollectionIfMissing(tCCOEFICIENTECollection, tCCOEFICIENTE);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOEFICIENTE);
        });

        it('should add only unique TCCOEFICIENTE to an array', () => {
          const tCCOEFICIENTEArray: ITCCOEFICIENTE[] = [{ idCoeficiente: 123 }, { idCoeficiente: 456 }, { idCoeficiente: 77350 }];
          const tCCOEFICIENTECollection: ITCCOEFICIENTE[] = [{ idCoeficiente: 123 }];
          expectedResult = service.addTCCOEFICIENTEToCollectionIfMissing(tCCOEFICIENTECollection, ...tCCOEFICIENTEArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCOEFICIENTE: ITCCOEFICIENTE = { idCoeficiente: 123 };
          const tCCOEFICIENTE2: ITCCOEFICIENTE = { idCoeficiente: 456 };
          expectedResult = service.addTCCOEFICIENTEToCollectionIfMissing([], tCCOEFICIENTE, tCCOEFICIENTE2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOEFICIENTE);
          expect(expectedResult).toContain(tCCOEFICIENTE2);
        });

        it('should accept null and undefined values', () => {
          const tCCOEFICIENTE: ITCCOEFICIENTE = { idCoeficiente: 123 };
          expectedResult = service.addTCCOEFICIENTEToCollectionIfMissing([], null, tCCOEFICIENTE, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOEFICIENTE);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
