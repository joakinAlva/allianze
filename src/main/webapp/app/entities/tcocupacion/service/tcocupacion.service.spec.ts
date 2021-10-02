import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCOCUPACION, TCOCUPACION } from '../tcocupacion.model';

import { TCOCUPACIONService } from './tcocupacion.service';

describe('Service Tests', () => {
  describe('TCOCUPACION Service', () => {
    let service: TCOCUPACIONService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCOCUPACION;
    let expectedResult: ITCOCUPACION | ITCOCUPACION[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCOCUPACIONService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idOcupacion: 0,
        ocupacion: 'AAAAAAA',
        factorGiroAnterior: 0,
        factorGiroActual: 0,
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

      it('should create a TCOCUPACION', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCOCUPACION()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCOCUPACION', () => {
        const returnedFromService = Object.assign(
          {
            idOcupacion: 1,
            ocupacion: 'BBBBBB',
            factorGiroAnterior: 1,
            factorGiroActual: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCOCUPACION', () => {
        const patchObject = Object.assign(
          {
            ocupacion: 'BBBBBB',
            factorGiroAnterior: 1,
          },
          new TCOCUPACION()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCOCUPACION', () => {
        const returnedFromService = Object.assign(
          {
            idOcupacion: 1,
            ocupacion: 'BBBBBB',
            factorGiroAnterior: 1,
            factorGiroActual: 1,
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

      it('should delete a TCOCUPACION', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCOCUPACIONToCollectionIfMissing', () => {
        it('should add a TCOCUPACION to an empty array', () => {
          const tCOCUPACION: ITCOCUPACION = { idOcupacion: 123 };
          expectedResult = service.addTCOCUPACIONToCollectionIfMissing([], tCOCUPACION);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCOCUPACION);
        });

        it('should not add a TCOCUPACION to an array that contains it', () => {
          const tCOCUPACION: ITCOCUPACION = { idOcupacion: 123 };
          const tCOCUPACIONCollection: ITCOCUPACION[] = [
            {
              ...tCOCUPACION,
            },
            { idOcupacion: 456 },
          ];
          expectedResult = service.addTCOCUPACIONToCollectionIfMissing(tCOCUPACIONCollection, tCOCUPACION);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCOCUPACION to an array that doesn't contain it", () => {
          const tCOCUPACION: ITCOCUPACION = { idOcupacion: 123 };
          const tCOCUPACIONCollection: ITCOCUPACION[] = [{ idOcupacion: 456 }];
          expectedResult = service.addTCOCUPACIONToCollectionIfMissing(tCOCUPACIONCollection, tCOCUPACION);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCOCUPACION);
        });

        it('should add only unique TCOCUPACION to an array', () => {
          const tCOCUPACIONArray: ITCOCUPACION[] = [{ idOcupacion: 123 }, { idOcupacion: 456 }, { idOcupacion: 83525 }];
          const tCOCUPACIONCollection: ITCOCUPACION[] = [{ idOcupacion: 123 }];
          expectedResult = service.addTCOCUPACIONToCollectionIfMissing(tCOCUPACIONCollection, ...tCOCUPACIONArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCOCUPACION: ITCOCUPACION = { idOcupacion: 123 };
          const tCOCUPACION2: ITCOCUPACION = { idOcupacion: 456 };
          expectedResult = service.addTCOCUPACIONToCollectionIfMissing([], tCOCUPACION, tCOCUPACION2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCOCUPACION);
          expect(expectedResult).toContain(tCOCUPACION2);
        });

        it('should accept null and undefined values', () => {
          const tCOCUPACION: ITCOCUPACION = { idOcupacion: 123 };
          expectedResult = service.addTCOCUPACIONToCollectionIfMissing([], null, tCOCUPACION, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCOCUPACION);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
