import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCONCEPTO, TCCONCEPTO } from '../tcconcepto.model';

import { TCCONCEPTOService } from './tcconcepto.service';

describe('Service Tests', () => {
  describe('TCCONCEPTO Service', () => {
    let service: TCCONCEPTOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCONCEPTO;
    let expectedResult: ITCCONCEPTO | ITCCONCEPTO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCONCEPTOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idConcepto: 0,
        concepto: 'AAAAAAA',
        dato: 0,
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

      it('should create a TCCONCEPTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCONCEPTO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCONCEPTO', () => {
        const returnedFromService = Object.assign(
          {
            idConcepto: 1,
            concepto: 'BBBBBB',
            dato: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCONCEPTO', () => {
        const patchObject = Object.assign(
          {
            concepto: 'BBBBBB',
          },
          new TCCONCEPTO()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCONCEPTO', () => {
        const returnedFromService = Object.assign(
          {
            idConcepto: 1,
            concepto: 'BBBBBB',
            dato: 1,
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

      it('should delete a TCCONCEPTO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCONCEPTOToCollectionIfMissing', () => {
        it('should add a TCCONCEPTO to an empty array', () => {
          const tCCONCEPTO: ITCCONCEPTO = { idConcepto: 123 };
          expectedResult = service.addTCCONCEPTOToCollectionIfMissing([], tCCONCEPTO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCONCEPTO);
        });

        it('should not add a TCCONCEPTO to an array that contains it', () => {
          const tCCONCEPTO: ITCCONCEPTO = { idConcepto: 123 };
          const tCCONCEPTOCollection: ITCCONCEPTO[] = [
            {
              ...tCCONCEPTO,
            },
            { idConcepto: 456 },
          ];
          expectedResult = service.addTCCONCEPTOToCollectionIfMissing(tCCONCEPTOCollection, tCCONCEPTO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCONCEPTO to an array that doesn't contain it", () => {
          const tCCONCEPTO: ITCCONCEPTO = { idConcepto: 123 };
          const tCCONCEPTOCollection: ITCCONCEPTO[] = [{ idConcepto: 456 }];
          expectedResult = service.addTCCONCEPTOToCollectionIfMissing(tCCONCEPTOCollection, tCCONCEPTO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCONCEPTO);
        });

        it('should add only unique TCCONCEPTO to an array', () => {
          const tCCONCEPTOArray: ITCCONCEPTO[] = [{ idConcepto: 123 }, { idConcepto: 456 }, { idConcepto: 36373 }];
          const tCCONCEPTOCollection: ITCCONCEPTO[] = [{ idConcepto: 123 }];
          expectedResult = service.addTCCONCEPTOToCollectionIfMissing(tCCONCEPTOCollection, ...tCCONCEPTOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCONCEPTO: ITCCONCEPTO = { idConcepto: 123 };
          const tCCONCEPTO2: ITCCONCEPTO = { idConcepto: 456 };
          expectedResult = service.addTCCONCEPTOToCollectionIfMissing([], tCCONCEPTO, tCCONCEPTO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCONCEPTO);
          expect(expectedResult).toContain(tCCONCEPTO2);
        });

        it('should accept null and undefined values', () => {
          const tCCONCEPTO: ITCCONCEPTO = { idConcepto: 123 };
          expectedResult = service.addTCCONCEPTOToCollectionIfMissing([], null, tCCONCEPTO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCONCEPTO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
