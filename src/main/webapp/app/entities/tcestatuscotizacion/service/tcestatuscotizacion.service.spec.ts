import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCESTATUSCOTIZACION, TCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';

import { TCESTATUSCOTIZACIONService } from './tcestatuscotizacion.service';

describe('Service Tests', () => {
  describe('TCESTATUSCOTIZACION Service', () => {
    let service: TCESTATUSCOTIZACIONService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCESTATUSCOTIZACION;
    let expectedResult: ITCESTATUSCOTIZACION | ITCESTATUSCOTIZACION[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCESTATUSCOTIZACIONService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idEstatusCotizacion: 0,
        orden: 0,
        estatusCotizacion: 'AAAAAAA',
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

      it('should create a TCESTATUSCOTIZACION', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCESTATUSCOTIZACION()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCESTATUSCOTIZACION', () => {
        const returnedFromService = Object.assign(
          {
            idEstatusCotizacion: 1,
            orden: 1,
            estatusCotizacion: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCESTATUSCOTIZACION', () => {
        const patchObject = Object.assign(
          {
            orden: 1,
            estatusCotizacion: 'BBBBBB',
          },
          new TCESTATUSCOTIZACION()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCESTATUSCOTIZACION', () => {
        const returnedFromService = Object.assign(
          {
            idEstatusCotizacion: 1,
            orden: 1,
            estatusCotizacion: 'BBBBBB',
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

      it('should delete a TCESTATUSCOTIZACION', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCESTATUSCOTIZACIONToCollectionIfMissing', () => {
        it('should add a TCESTATUSCOTIZACION to an empty array', () => {
          const tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION = { idEstatusCotizacion: 123 };
          expectedResult = service.addTCESTATUSCOTIZACIONToCollectionIfMissing([], tCESTATUSCOTIZACION);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCESTATUSCOTIZACION);
        });

        it('should not add a TCESTATUSCOTIZACION to an array that contains it', () => {
          const tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION = { idEstatusCotizacion: 123 };
          const tCESTATUSCOTIZACIONCollection: ITCESTATUSCOTIZACION[] = [
            {
              ...tCESTATUSCOTIZACION,
            },
            { idEstatusCotizacion: 456 },
          ];
          expectedResult = service.addTCESTATUSCOTIZACIONToCollectionIfMissing(tCESTATUSCOTIZACIONCollection, tCESTATUSCOTIZACION);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCESTATUSCOTIZACION to an array that doesn't contain it", () => {
          const tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION = { idEstatusCotizacion: 123 };
          const tCESTATUSCOTIZACIONCollection: ITCESTATUSCOTIZACION[] = [{ idEstatusCotizacion: 456 }];
          expectedResult = service.addTCESTATUSCOTIZACIONToCollectionIfMissing(tCESTATUSCOTIZACIONCollection, tCESTATUSCOTIZACION);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCESTATUSCOTIZACION);
        });

        it('should add only unique TCESTATUSCOTIZACION to an array', () => {
          const tCESTATUSCOTIZACIONArray: ITCESTATUSCOTIZACION[] = [
            { idEstatusCotizacion: 123 },
            { idEstatusCotizacion: 456 },
            { idEstatusCotizacion: 47714 },
          ];
          const tCESTATUSCOTIZACIONCollection: ITCESTATUSCOTIZACION[] = [{ idEstatusCotizacion: 123 }];
          expectedResult = service.addTCESTATUSCOTIZACIONToCollectionIfMissing(tCESTATUSCOTIZACIONCollection, ...tCESTATUSCOTIZACIONArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION = { idEstatusCotizacion: 123 };
          const tCESTATUSCOTIZACION2: ITCESTATUSCOTIZACION = { idEstatusCotizacion: 456 };
          expectedResult = service.addTCESTATUSCOTIZACIONToCollectionIfMissing([], tCESTATUSCOTIZACION, tCESTATUSCOTIZACION2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCESTATUSCOTIZACION);
          expect(expectedResult).toContain(tCESTATUSCOTIZACION2);
        });

        it('should accept null and undefined values', () => {
          const tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION = { idEstatusCotizacion: 123 };
          expectedResult = service.addTCESTATUSCOTIZACIONToCollectionIfMissing([], null, tCESTATUSCOTIZACION, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCESTATUSCOTIZACION);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
