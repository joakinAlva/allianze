import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCOVID, TCCOVID } from '../tccovid.model';

import { TCCOVIDService } from './tccovid.service';

describe('Service Tests', () => {
  describe('TCCOVID Service', () => {
    let service: TCCOVIDService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCOVID;
    let expectedResult: ITCCOVID | ITCCOVID[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCOVIDService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCovid: 0,
        edad: 'AAAAAAA',
        basica: 0,
        recargoEdad: 0,
        recargoGiro: 0,
        recargoTotal: 0,
        basicaRecargada: 0,
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

      it('should create a TCCOVID', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCOVID()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCOVID', () => {
        const returnedFromService = Object.assign(
          {
            idCovid: 1,
            edad: 'BBBBBB',
            basica: 1,
            recargoEdad: 1,
            recargoGiro: 1,
            recargoTotal: 1,
            basicaRecargada: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCOVID', () => {
        const patchObject = Object.assign(
          {
            recargoEdad: 1,
            recargoGiro: 1,
            recargoTotal: 1,
          },
          new TCCOVID()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCOVID', () => {
        const returnedFromService = Object.assign(
          {
            idCovid: 1,
            edad: 'BBBBBB',
            basica: 1,
            recargoEdad: 1,
            recargoGiro: 1,
            recargoTotal: 1,
            basicaRecargada: 1,
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

      it('should delete a TCCOVID', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCOVIDToCollectionIfMissing', () => {
        it('should add a TCCOVID to an empty array', () => {
          const tCCOVID: ITCCOVID = { idCovid: 123 };
          expectedResult = service.addTCCOVIDToCollectionIfMissing([], tCCOVID);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOVID);
        });

        it('should not add a TCCOVID to an array that contains it', () => {
          const tCCOVID: ITCCOVID = { idCovid: 123 };
          const tCCOVIDCollection: ITCCOVID[] = [
            {
              ...tCCOVID,
            },
            { idCovid: 456 },
          ];
          expectedResult = service.addTCCOVIDToCollectionIfMissing(tCCOVIDCollection, tCCOVID);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCOVID to an array that doesn't contain it", () => {
          const tCCOVID: ITCCOVID = { idCovid: 123 };
          const tCCOVIDCollection: ITCCOVID[] = [{ idCovid: 456 }];
          expectedResult = service.addTCCOVIDToCollectionIfMissing(tCCOVIDCollection, tCCOVID);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOVID);
        });

        it('should add only unique TCCOVID to an array', () => {
          const tCCOVIDArray: ITCCOVID[] = [{ idCovid: 123 }, { idCovid: 456 }, { idCovid: 4061 }];
          const tCCOVIDCollection: ITCCOVID[] = [{ idCovid: 123 }];
          expectedResult = service.addTCCOVIDToCollectionIfMissing(tCCOVIDCollection, ...tCCOVIDArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCOVID: ITCCOVID = { idCovid: 123 };
          const tCCOVID2: ITCCOVID = { idCovid: 456 };
          expectedResult = service.addTCCOVIDToCollectionIfMissing([], tCCOVID, tCCOVID2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCOVID);
          expect(expectedResult).toContain(tCCOVID2);
        });

        it('should accept null and undefined values', () => {
          const tCCOVID: ITCCOVID = { idCovid: 123 };
          expectedResult = service.addTCCOVIDToCollectionIfMissing([], null, tCCOVID, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCOVID);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
