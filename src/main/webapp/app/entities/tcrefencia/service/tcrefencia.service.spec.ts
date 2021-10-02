import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCREFENCIA, TCREFENCIA } from '../tcrefencia.model';

import { TCREFENCIAService } from './tcrefencia.service';

describe('Service Tests', () => {
  describe('TCREFENCIA Service', () => {
    let service: TCREFENCIAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCREFENCIA;
    let expectedResult: ITCREFENCIA | ITCREFENCIA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCREFENCIAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idReferencia: 0,
        periodo: 'AAAAAAA',
        referencia: 'AAAAAAA',
        edadpromedio: 'AAAAAAA',
        cuota: 'AAAAAAA',
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

      it('should create a TCREFENCIA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCREFENCIA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCREFENCIA', () => {
        const returnedFromService = Object.assign(
          {
            idReferencia: 1,
            periodo: 'BBBBBB',
            referencia: 'BBBBBB',
            edadpromedio: 'BBBBBB',
            cuota: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCREFENCIA', () => {
        const patchObject = Object.assign({}, new TCREFENCIA());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCREFENCIA', () => {
        const returnedFromService = Object.assign(
          {
            idReferencia: 1,
            periodo: 'BBBBBB',
            referencia: 'BBBBBB',
            edadpromedio: 'BBBBBB',
            cuota: 'BBBBBB',
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

      it('should delete a TCREFENCIA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCREFENCIAToCollectionIfMissing', () => {
        it('should add a TCREFENCIA to an empty array', () => {
          const tCREFENCIA: ITCREFENCIA = { idReferencia: 123 };
          expectedResult = service.addTCREFENCIAToCollectionIfMissing([], tCREFENCIA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCREFENCIA);
        });

        it('should not add a TCREFENCIA to an array that contains it', () => {
          const tCREFENCIA: ITCREFENCIA = { idReferencia: 123 };
          const tCREFENCIACollection: ITCREFENCIA[] = [
            {
              ...tCREFENCIA,
            },
            { idReferencia: 456 },
          ];
          expectedResult = service.addTCREFENCIAToCollectionIfMissing(tCREFENCIACollection, tCREFENCIA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCREFENCIA to an array that doesn't contain it", () => {
          const tCREFENCIA: ITCREFENCIA = { idReferencia: 123 };
          const tCREFENCIACollection: ITCREFENCIA[] = [{ idReferencia: 456 }];
          expectedResult = service.addTCREFENCIAToCollectionIfMissing(tCREFENCIACollection, tCREFENCIA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCREFENCIA);
        });

        it('should add only unique TCREFENCIA to an array', () => {
          const tCREFENCIAArray: ITCREFENCIA[] = [{ idReferencia: 123 }, { idReferencia: 456 }, { idReferencia: 46633 }];
          const tCREFENCIACollection: ITCREFENCIA[] = [{ idReferencia: 123 }];
          expectedResult = service.addTCREFENCIAToCollectionIfMissing(tCREFENCIACollection, ...tCREFENCIAArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCREFENCIA: ITCREFENCIA = { idReferencia: 123 };
          const tCREFENCIA2: ITCREFENCIA = { idReferencia: 456 };
          expectedResult = service.addTCREFENCIAToCollectionIfMissing([], tCREFENCIA, tCREFENCIA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCREFENCIA);
          expect(expectedResult).toContain(tCREFENCIA2);
        });

        it('should accept null and undefined values', () => {
          const tCREFENCIA: ITCREFENCIA = { idReferencia: 123 };
          expectedResult = service.addTCREFENCIAToCollectionIfMissing([], null, tCREFENCIA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCREFENCIA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
