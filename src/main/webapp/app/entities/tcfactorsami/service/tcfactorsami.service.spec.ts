import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCFACTORSAMI, TCFACTORSAMI } from '../tcfactorsami.model';

import { TCFACTORSAMIService } from './tcfactorsami.service';

describe('Service Tests', () => {
  describe('TCFACTORSAMI Service', () => {
    let service: TCFACTORSAMIService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCFACTORSAMI;
    let expectedResult: ITCFACTORSAMI | ITCFACTORSAMI[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCFACTORSAMIService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idFactorSami: 0,
        minAsegurados: 'AAAAAAA',
        maxAsegurados: 'AAAAAAA',
        factor: 'AAAAAAA',
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

      it('should create a TCFACTORSAMI', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCFACTORSAMI()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCFACTORSAMI', () => {
        const returnedFromService = Object.assign(
          {
            idFactorSami: 1,
            minAsegurados: 'BBBBBB',
            maxAsegurados: 'BBBBBB',
            factor: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCFACTORSAMI', () => {
        const patchObject = Object.assign(
          {
            maxAsegurados: 'BBBBBB',
            factor: 'BBBBBB',
          },
          new TCFACTORSAMI()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCFACTORSAMI', () => {
        const returnedFromService = Object.assign(
          {
            idFactorSami: 1,
            minAsegurados: 'BBBBBB',
            maxAsegurados: 'BBBBBB',
            factor: 'BBBBBB',
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

      it('should delete a TCFACTORSAMI', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCFACTORSAMIToCollectionIfMissing', () => {
        it('should add a TCFACTORSAMI to an empty array', () => {
          const tCFACTORSAMI: ITCFACTORSAMI = { idFactorSami: 123 };
          expectedResult = service.addTCFACTORSAMIToCollectionIfMissing([], tCFACTORSAMI);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCFACTORSAMI);
        });

        it('should not add a TCFACTORSAMI to an array that contains it', () => {
          const tCFACTORSAMI: ITCFACTORSAMI = { idFactorSami: 123 };
          const tCFACTORSAMICollection: ITCFACTORSAMI[] = [
            {
              ...tCFACTORSAMI,
            },
            { idFactorSami: 456 },
          ];
          expectedResult = service.addTCFACTORSAMIToCollectionIfMissing(tCFACTORSAMICollection, tCFACTORSAMI);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCFACTORSAMI to an array that doesn't contain it", () => {
          const tCFACTORSAMI: ITCFACTORSAMI = { idFactorSami: 123 };
          const tCFACTORSAMICollection: ITCFACTORSAMI[] = [{ idFactorSami: 456 }];
          expectedResult = service.addTCFACTORSAMIToCollectionIfMissing(tCFACTORSAMICollection, tCFACTORSAMI);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCFACTORSAMI);
        });

        it('should add only unique TCFACTORSAMI to an array', () => {
          const tCFACTORSAMIArray: ITCFACTORSAMI[] = [{ idFactorSami: 123 }, { idFactorSami: 456 }, { idFactorSami: 84762 }];
          const tCFACTORSAMICollection: ITCFACTORSAMI[] = [{ idFactorSami: 123 }];
          expectedResult = service.addTCFACTORSAMIToCollectionIfMissing(tCFACTORSAMICollection, ...tCFACTORSAMIArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCFACTORSAMI: ITCFACTORSAMI = { idFactorSami: 123 };
          const tCFACTORSAMI2: ITCFACTORSAMI = { idFactorSami: 456 };
          expectedResult = service.addTCFACTORSAMIToCollectionIfMissing([], tCFACTORSAMI, tCFACTORSAMI2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCFACTORSAMI);
          expect(expectedResult).toContain(tCFACTORSAMI2);
        });

        it('should accept null and undefined values', () => {
          const tCFACTORSAMI: ITCFACTORSAMI = { idFactorSami: 123 };
          expectedResult = service.addTCFACTORSAMIToCollectionIfMissing([], null, tCFACTORSAMI, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCFACTORSAMI);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
