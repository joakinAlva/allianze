import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCDESCUENTOSUMAASEGURADA, TCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';

import { TCDESCUENTOSUMAASEGURADAService } from './tcdescuentosumaasegurada.service';

describe('Service Tests', () => {
  describe('TCDESCUENTOSUMAASEGURADA Service', () => {
    let service: TCDESCUENTOSUMAASEGURADAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCDESCUENTOSUMAASEGURADA;
    let expectedResult: ITCDESCUENTOSUMAASEGURADA | ITCDESCUENTOSUMAASEGURADA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCDESCUENTOSUMAASEGURADAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idDescuentoSumaAsegurada: 0,
        minSuma: 'AAAAAAA',
        maxSuma: 'AAAAAAA',
        porcentaje: 0,
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

      it('should create a TCDESCUENTOSUMAASEGURADA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCDESCUENTOSUMAASEGURADA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCDESCUENTOSUMAASEGURADA', () => {
        const returnedFromService = Object.assign(
          {
            idDescuentoSumaAsegurada: 1,
            minSuma: 'BBBBBB',
            maxSuma: 'BBBBBB',
            porcentaje: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCDESCUENTOSUMAASEGURADA', () => {
        const patchObject = Object.assign(
          {
            maxSuma: 'BBBBBB',
            porcentaje: 1,
          },
          new TCDESCUENTOSUMAASEGURADA()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCDESCUENTOSUMAASEGURADA', () => {
        const returnedFromService = Object.assign(
          {
            idDescuentoSumaAsegurada: 1,
            minSuma: 'BBBBBB',
            maxSuma: 'BBBBBB',
            porcentaje: 1,
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

      it('should delete a TCDESCUENTOSUMAASEGURADA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing', () => {
        it('should add a TCDESCUENTOSUMAASEGURADA to an empty array', () => {
          const tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 123 };
          expectedResult = service.addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing([], tCDESCUENTOSUMAASEGURADA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCDESCUENTOSUMAASEGURADA);
        });

        it('should not add a TCDESCUENTOSUMAASEGURADA to an array that contains it', () => {
          const tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 123 };
          const tCDESCUENTOSUMAASEGURADACollection: ITCDESCUENTOSUMAASEGURADA[] = [
            {
              ...tCDESCUENTOSUMAASEGURADA,
            },
            { idDescuentoSumaAsegurada: 456 },
          ];
          expectedResult = service.addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing(
            tCDESCUENTOSUMAASEGURADACollection,
            tCDESCUENTOSUMAASEGURADA
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCDESCUENTOSUMAASEGURADA to an array that doesn't contain it", () => {
          const tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 123 };
          const tCDESCUENTOSUMAASEGURADACollection: ITCDESCUENTOSUMAASEGURADA[] = [{ idDescuentoSumaAsegurada: 456 }];
          expectedResult = service.addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing(
            tCDESCUENTOSUMAASEGURADACollection,
            tCDESCUENTOSUMAASEGURADA
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCDESCUENTOSUMAASEGURADA);
        });

        it('should add only unique TCDESCUENTOSUMAASEGURADA to an array', () => {
          const tCDESCUENTOSUMAASEGURADAArray: ITCDESCUENTOSUMAASEGURADA[] = [
            { idDescuentoSumaAsegurada: 123 },
            { idDescuentoSumaAsegurada: 456 },
            { idDescuentoSumaAsegurada: 92188 },
          ];
          const tCDESCUENTOSUMAASEGURADACollection: ITCDESCUENTOSUMAASEGURADA[] = [{ idDescuentoSumaAsegurada: 123 }];
          expectedResult = service.addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing(
            tCDESCUENTOSUMAASEGURADACollection,
            ...tCDESCUENTOSUMAASEGURADAArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 123 };
          const tCDESCUENTOSUMAASEGURADA2: ITCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 456 };
          expectedResult = service.addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing(
            [],
            tCDESCUENTOSUMAASEGURADA,
            tCDESCUENTOSUMAASEGURADA2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCDESCUENTOSUMAASEGURADA);
          expect(expectedResult).toContain(tCDESCUENTOSUMAASEGURADA2);
        });

        it('should accept null and undefined values', () => {
          const tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 123 };
          expectedResult = service.addTCDESCUENTOSUMAASEGURADAToCollectionIfMissing([], null, tCDESCUENTOSUMAASEGURADA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCDESCUENTOSUMAASEGURADA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
