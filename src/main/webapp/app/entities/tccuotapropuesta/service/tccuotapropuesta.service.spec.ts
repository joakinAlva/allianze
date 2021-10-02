import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCCUOTAPROPUESTA, TCCUOTAPROPUESTA } from '../tccuotapropuesta.model';

import { TCCUOTAPROPUESTAService } from './tccuotapropuesta.service';

describe('Service Tests', () => {
  describe('TCCUOTAPROPUESTA Service', () => {
    let service: TCCUOTAPROPUESTAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCCUOTAPROPUESTA;
    let expectedResult: ITCCUOTAPROPUESTA | ITCCUOTAPROPUESTA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCCUOTAPROPUESTAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCuotaPropuesta: 0,
        edad: 'AAAAAAA',
        valorVg: 0,
        valorBipTres: 0,
        valorBit: 0,
        valorDi: 0,
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

      it('should create a TCCUOTAPROPUESTA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCCUOTAPROPUESTA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCCUOTAPROPUESTA', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaPropuesta: 1,
            edad: 'BBBBBB',
            valorVg: 1,
            valorBipTres: 1,
            valorBit: 1,
            valorDi: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCCUOTAPROPUESTA', () => {
        const patchObject = Object.assign(
          {
            valorVg: 1,
            valorBipTres: 1,
            valorBit: 1,
            valorDi: 1,
          },
          new TCCUOTAPROPUESTA()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCCUOTAPROPUESTA', () => {
        const returnedFromService = Object.assign(
          {
            idCuotaPropuesta: 1,
            edad: 'BBBBBB',
            valorVg: 1,
            valorBipTres: 1,
            valorBit: 1,
            valorDi: 1,
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

      it('should delete a TCCUOTAPROPUESTA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCCUOTAPROPUESTAToCollectionIfMissing', () => {
        it('should add a TCCUOTAPROPUESTA to an empty array', () => {
          const tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA = { idCuotaPropuesta: 123 };
          expectedResult = service.addTCCUOTAPROPUESTAToCollectionIfMissing([], tCCUOTAPROPUESTA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTAPROPUESTA);
        });

        it('should not add a TCCUOTAPROPUESTA to an array that contains it', () => {
          const tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA = { idCuotaPropuesta: 123 };
          const tCCUOTAPROPUESTACollection: ITCCUOTAPROPUESTA[] = [
            {
              ...tCCUOTAPROPUESTA,
            },
            { idCuotaPropuesta: 456 },
          ];
          expectedResult = service.addTCCUOTAPROPUESTAToCollectionIfMissing(tCCUOTAPROPUESTACollection, tCCUOTAPROPUESTA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCCUOTAPROPUESTA to an array that doesn't contain it", () => {
          const tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA = { idCuotaPropuesta: 123 };
          const tCCUOTAPROPUESTACollection: ITCCUOTAPROPUESTA[] = [{ idCuotaPropuesta: 456 }];
          expectedResult = service.addTCCUOTAPROPUESTAToCollectionIfMissing(tCCUOTAPROPUESTACollection, tCCUOTAPROPUESTA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTAPROPUESTA);
        });

        it('should add only unique TCCUOTAPROPUESTA to an array', () => {
          const tCCUOTAPROPUESTAArray: ITCCUOTAPROPUESTA[] = [
            { idCuotaPropuesta: 123 },
            { idCuotaPropuesta: 456 },
            { idCuotaPropuesta: 81275 },
          ];
          const tCCUOTAPROPUESTACollection: ITCCUOTAPROPUESTA[] = [{ idCuotaPropuesta: 123 }];
          expectedResult = service.addTCCUOTAPROPUESTAToCollectionIfMissing(tCCUOTAPROPUESTACollection, ...tCCUOTAPROPUESTAArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA = { idCuotaPropuesta: 123 };
          const tCCUOTAPROPUESTA2: ITCCUOTAPROPUESTA = { idCuotaPropuesta: 456 };
          expectedResult = service.addTCCUOTAPROPUESTAToCollectionIfMissing([], tCCUOTAPROPUESTA, tCCUOTAPROPUESTA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCCUOTAPROPUESTA);
          expect(expectedResult).toContain(tCCUOTAPROPUESTA2);
        });

        it('should accept null and undefined values', () => {
          const tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA = { idCuotaPropuesta: 123 };
          expectedResult = service.addTCCUOTAPROPUESTAToCollectionIfMissing([], null, tCCUOTAPROPUESTA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCCUOTAPROPUESTA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
