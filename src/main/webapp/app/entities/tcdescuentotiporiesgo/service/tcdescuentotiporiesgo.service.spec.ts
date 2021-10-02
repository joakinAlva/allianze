import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCDESCUENTOTIPORIESGO, TCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';

import { TCDESCUENTOTIPORIESGOService } from './tcdescuentotiporiesgo.service';

describe('Service Tests', () => {
  describe('TCDESCUENTOTIPORIESGO Service', () => {
    let service: TCDESCUENTOTIPORIESGOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCDESCUENTOTIPORIESGO;
    let expectedResult: ITCDESCUENTOTIPORIESGO | ITCDESCUENTOTIPORIESGO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCDESCUENTOTIPORIESGOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idDescuentoTipoRiesgo: 0,
        tipo: 'AAAAAAA',
        descuento: 0,
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

      it('should create a TCDESCUENTOTIPORIESGO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCDESCUENTOTIPORIESGO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCDESCUENTOTIPORIESGO', () => {
        const returnedFromService = Object.assign(
          {
            idDescuentoTipoRiesgo: 1,
            tipo: 'BBBBBB',
            descuento: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCDESCUENTOTIPORIESGO', () => {
        const patchObject = Object.assign(
          {
            tipo: 'BBBBBB',
          },
          new TCDESCUENTOTIPORIESGO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCDESCUENTOTIPORIESGO', () => {
        const returnedFromService = Object.assign(
          {
            idDescuentoTipoRiesgo: 1,
            tipo: 'BBBBBB',
            descuento: 1,
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

      it('should delete a TCDESCUENTOTIPORIESGO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCDESCUENTOTIPORIESGOToCollectionIfMissing', () => {
        it('should add a TCDESCUENTOTIPORIESGO to an empty array', () => {
          const tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 123 };
          expectedResult = service.addTCDESCUENTOTIPORIESGOToCollectionIfMissing([], tCDESCUENTOTIPORIESGO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCDESCUENTOTIPORIESGO);
        });

        it('should not add a TCDESCUENTOTIPORIESGO to an array that contains it', () => {
          const tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 123 };
          const tCDESCUENTOTIPORIESGOCollection: ITCDESCUENTOTIPORIESGO[] = [
            {
              ...tCDESCUENTOTIPORIESGO,
            },
            { idDescuentoTipoRiesgo: 456 },
          ];
          expectedResult = service.addTCDESCUENTOTIPORIESGOToCollectionIfMissing(tCDESCUENTOTIPORIESGOCollection, tCDESCUENTOTIPORIESGO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCDESCUENTOTIPORIESGO to an array that doesn't contain it", () => {
          const tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 123 };
          const tCDESCUENTOTIPORIESGOCollection: ITCDESCUENTOTIPORIESGO[] = [{ idDescuentoTipoRiesgo: 456 }];
          expectedResult = service.addTCDESCUENTOTIPORIESGOToCollectionIfMissing(tCDESCUENTOTIPORIESGOCollection, tCDESCUENTOTIPORIESGO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCDESCUENTOTIPORIESGO);
        });

        it('should add only unique TCDESCUENTOTIPORIESGO to an array', () => {
          const tCDESCUENTOTIPORIESGOArray: ITCDESCUENTOTIPORIESGO[] = [
            { idDescuentoTipoRiesgo: 123 },
            { idDescuentoTipoRiesgo: 456 },
            { idDescuentoTipoRiesgo: 48802 },
          ];
          const tCDESCUENTOTIPORIESGOCollection: ITCDESCUENTOTIPORIESGO[] = [{ idDescuentoTipoRiesgo: 123 }];
          expectedResult = service.addTCDESCUENTOTIPORIESGOToCollectionIfMissing(
            tCDESCUENTOTIPORIESGOCollection,
            ...tCDESCUENTOTIPORIESGOArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 123 };
          const tCDESCUENTOTIPORIESGO2: ITCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 456 };
          expectedResult = service.addTCDESCUENTOTIPORIESGOToCollectionIfMissing([], tCDESCUENTOTIPORIESGO, tCDESCUENTOTIPORIESGO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCDESCUENTOTIPORIESGO);
          expect(expectedResult).toContain(tCDESCUENTOTIPORIESGO2);
        });

        it('should accept null and undefined values', () => {
          const tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO = { idDescuentoTipoRiesgo: 123 };
          expectedResult = service.addTCDESCUENTOTIPORIESGOToCollectionIfMissing([], null, tCDESCUENTOTIPORIESGO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCDESCUENTOTIPORIESGO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
