import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCTIPO, TCTIPO } from '../tctipo.model';

import { TCTIPOService } from './tctipo.service';

describe('Service Tests', () => {
  describe('TCTIPO Service', () => {
    let service: TCTIPOService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCTIPO;
    let expectedResult: ITCTIPO | ITCTIPO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCTIPOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idTipo: 0,
        tipoRegla: 'AAAAAAA',
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

      it('should create a TCTIPO', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCTIPO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCTIPO', () => {
        const returnedFromService = Object.assign(
          {
            idTipo: 1,
            tipoRegla: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCTIPO', () => {
        const patchObject = Object.assign({}, new TCTIPO());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCTIPO', () => {
        const returnedFromService = Object.assign(
          {
            idTipo: 1,
            tipoRegla: 'BBBBBB',
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

      it('should delete a TCTIPO', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCTIPOToCollectionIfMissing', () => {
        it('should add a TCTIPO to an empty array', () => {
          const tCTIPO: ITCTIPO = { idTipo: 123 };
          expectedResult = service.addTCTIPOToCollectionIfMissing([], tCTIPO);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCTIPO);
        });

        it('should not add a TCTIPO to an array that contains it', () => {
          const tCTIPO: ITCTIPO = { idTipo: 123 };
          const tCTIPOCollection: ITCTIPO[] = [
            {
              ...tCTIPO,
            },
            { idTipo: 456 },
          ];
          expectedResult = service.addTCTIPOToCollectionIfMissing(tCTIPOCollection, tCTIPO);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCTIPO to an array that doesn't contain it", () => {
          const tCTIPO: ITCTIPO = { idTipo: 123 };
          const tCTIPOCollection: ITCTIPO[] = [{ idTipo: 456 }];
          expectedResult = service.addTCTIPOToCollectionIfMissing(tCTIPOCollection, tCTIPO);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCTIPO);
        });

        it('should add only unique TCTIPO to an array', () => {
          const tCTIPOArray: ITCTIPO[] = [{ idTipo: 123 }, { idTipo: 456 }, { idTipo: 22884 }];
          const tCTIPOCollection: ITCTIPO[] = [{ idTipo: 123 }];
          expectedResult = service.addTCTIPOToCollectionIfMissing(tCTIPOCollection, ...tCTIPOArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCTIPO: ITCTIPO = { idTipo: 123 };
          const tCTIPO2: ITCTIPO = { idTipo: 456 };
          expectedResult = service.addTCTIPOToCollectionIfMissing([], tCTIPO, tCTIPO2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCTIPO);
          expect(expectedResult).toContain(tCTIPO2);
        });

        it('should accept null and undefined values', () => {
          const tCTIPO: ITCTIPO = { idTipo: 123 };
          expectedResult = service.addTCTIPOToCollectionIfMissing([], null, tCTIPO, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCTIPO);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
