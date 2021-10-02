import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCOBERTURA, TCCOBERTURA } from '../tccobertura.model';

import { TCCOBERTURAService } from './tccobertura.service';

describe('Service Tests', () => {
  describe('TCCOBERTURA Service', () => {
    let service: TCCOBERTURAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCOBERTURA;
    let expectedResult: ITCCOBERTURA | ITCCOBERTURA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCOBERTURAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCobertura: 0,
        cobertura: 'AAAAAAA',
        posicion: 0,
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

      it('should create a TCCOBERTURA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCOBERTURA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCOBERTURA', () => {
        const returnedFromService = Object.assign(
          {
            idCobertura: 1,
            cobertura: 'BBBBBB',
            posicion: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCOBERTURA', () => {
        const patchObject = Object.assign({}, new TCCOBERTURA());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCOBERTURA', () => {
        const returnedFromService = Object.assign(
          {
            idCobertura: 1,
            cobertura: 'BBBBBB',
            posicion: 1,
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

      it('should delete a TCCOBERTURA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCOBERTURAToCollectionIfMissing', () => {
        it('should add a TCCOBERTURA to an empty array', () => {
          const tCCOBERTURA: ITCCOBERTURA = { idCobertura: 123 };
          expectedResult = service.addTCCOBERTURAToCollectionIfMissing([], tCCOBERTURA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOBERTURA);
        });

        it('should not add a TCCOBERTURA to an array that contains it', () => {
          const tCCOBERTURA: ITCCOBERTURA = { idCobertura: 123 };
          const tCCOBERTURACollection: ITCCOBERTURA[] = [
            {
              ...tCCOBERTURA,
            },
            { idCobertura: 456 },
          ];
          expectedResult = service.addTCCOBERTURAToCollectionIfMissing(tCCOBERTURACollection, tCCOBERTURA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCOBERTURA to an array that doesn't contain it", () => {
          const tCCOBERTURA: ITCCOBERTURA = { idCobertura: 123 };
          const tCCOBERTURACollection: ITCCOBERTURA[] = [{ idCobertura: 456 }];
          expectedResult = service.addTCCOBERTURAToCollectionIfMissing(tCCOBERTURACollection, tCCOBERTURA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOBERTURA);
        });

        it('should add only unique TCCOBERTURA to an array', () => {
          const tCCOBERTURAArray: ITCCOBERTURA[] = [{ idCobertura: 123 }, { idCobertura: 456 }, { idCobertura: 70589 }];
          const tCCOBERTURACollection: ITCCOBERTURA[] = [{ idCobertura: 123 }];
          expectedResult = service.addTCCOBERTURAToCollectionIfMissing(tCCOBERTURACollection, ...tCCOBERTURAArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCOBERTURA: ITCCOBERTURA = { idCobertura: 123 };
          const tCCOBERTURA2: ITCCOBERTURA = { idCobertura: 456 };
          expectedResult = service.addTCCOBERTURAToCollectionIfMissing([], tCCOBERTURA, tCCOBERTURA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOBERTURA);
          expect(expectedResult).toContain(tCCOBERTURA2);
        });

        it('should accept null and undefined values', () => {
          const tCCOBERTURA: ITCCOBERTURA = { idCobertura: 123 };
          expectedResult = service.addTCCOBERTURAToCollectionIfMissing([], null, tCCOBERTURA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOBERTURA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
