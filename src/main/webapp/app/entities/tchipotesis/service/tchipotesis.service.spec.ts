import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCHIPOTESIS, TCHIPOTESIS } from '../tchipotesis.model';

import { TCHIPOTESISService } from './tchipotesis.service';

describe('Service Tests', () => {
  describe('TCHIPOTESIS Service', () => {
    let service: TCHIPOTESISService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCHIPOTESIS;
    let expectedResult: ITCHIPOTESIS | ITCHIPOTESIS[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCHIPOTESISService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idHipotesis: 0,
        hipotesis: 'AAAAAAA',
        valor: 0,
        variable: 'AAAAAAA',
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

      it('should create a TCHIPOTESIS', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCHIPOTESIS()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCHIPOTESIS', () => {
        const returnedFromService = Object.assign(
          {
            idHipotesis: 1,
            hipotesis: 'BBBBBB',
            valor: 1,
            variable: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCHIPOTESIS', () => {
        const patchObject = Object.assign(
          {
            hipotesis: 'BBBBBB',
            valor: 1,
            variable: 'BBBBBB',
          },
          new TCHIPOTESIS()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCHIPOTESIS', () => {
        const returnedFromService = Object.assign(
          {
            idHipotesis: 1,
            hipotesis: 'BBBBBB',
            valor: 1,
            variable: 'BBBBBB',
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

      it('should delete a TCHIPOTESIS', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCHIPOTESISToCollectionIfMissing', () => {
        it('should add a TCHIPOTESIS to an empty array', () => {
          const tCHIPOTESIS: ITCHIPOTESIS = { idHipotesis: 123 };
          expectedResult = service.addTCHIPOTESISToCollectionIfMissing([], tCHIPOTESIS);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCHIPOTESIS);
        });

        it('should not add a TCHIPOTESIS to an array that contains it', () => {
          const tCHIPOTESIS: ITCHIPOTESIS = { idHipotesis: 123 };
          const tCHIPOTESISCollection: ITCHIPOTESIS[] = [
            {
              ...tCHIPOTESIS,
            },
            { idHipotesis: 456 },
          ];
          expectedResult = service.addTCHIPOTESISToCollectionIfMissing(tCHIPOTESISCollection, tCHIPOTESIS);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCHIPOTESIS to an array that doesn't contain it", () => {
          const tCHIPOTESIS: ITCHIPOTESIS = { idHipotesis: 123 };
          const tCHIPOTESISCollection: ITCHIPOTESIS[] = [{ idHipotesis: 456 }];
          expectedResult = service.addTCHIPOTESISToCollectionIfMissing(tCHIPOTESISCollection, tCHIPOTESIS);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCHIPOTESIS);
        });

        it('should add only unique TCHIPOTESIS to an array', () => {
          const tCHIPOTESISArray: ITCHIPOTESIS[] = [{ idHipotesis: 123 }, { idHipotesis: 456 }, { idHipotesis: 80958 }];
          const tCHIPOTESISCollection: ITCHIPOTESIS[] = [{ idHipotesis: 123 }];
          expectedResult = service.addTCHIPOTESISToCollectionIfMissing(tCHIPOTESISCollection, ...tCHIPOTESISArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCHIPOTESIS: ITCHIPOTESIS = { idHipotesis: 123 };
          const tCHIPOTESIS2: ITCHIPOTESIS = { idHipotesis: 456 };
          expectedResult = service.addTCHIPOTESISToCollectionIfMissing([], tCHIPOTESIS, tCHIPOTESIS2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCHIPOTESIS);
          expect(expectedResult).toContain(tCHIPOTESIS2);
        });

        it('should accept null and undefined values', () => {
          const tCHIPOTESIS: ITCHIPOTESIS = { idHipotesis: 123 };
          expectedResult = service.addTCHIPOTESISToCollectionIfMissing([], null, tCHIPOTESIS, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCHIPOTESIS);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
