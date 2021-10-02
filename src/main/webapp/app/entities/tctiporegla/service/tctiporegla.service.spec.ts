import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITCTIPOREGLA, TCTIPOREGLA } from '../tctiporegla.model';

import { TCTIPOREGLAService } from './tctiporegla.service';

describe('Service Tests', () => {
  describe('TCTIPOREGLA Service', () => {
    let service: TCTIPOREGLAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITCTIPOREGLA;
    let expectedResult: ITCTIPOREGLA | ITCTIPOREGLA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TCTIPOREGLAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idTipoRegla: 0,
        tipo: 'AAAAAAA',
        segmento: 0,
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

      it('should create a TCTIPOREGLA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TCTIPOREGLA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TCTIPOREGLA', () => {
        const returnedFromService = Object.assign(
          {
            idTipoRegla: 1,
            tipo: 'BBBBBB',
            segmento: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TCTIPOREGLA', () => {
        const patchObject = Object.assign(
          {
            segmento: 1,
          },
          new TCTIPOREGLA()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TCTIPOREGLA', () => {
        const returnedFromService = Object.assign(
          {
            idTipoRegla: 1,
            tipo: 'BBBBBB',
            segmento: 1,
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

      it('should delete a TCTIPOREGLA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTCTIPOREGLAToCollectionIfMissing', () => {
        it('should add a TCTIPOREGLA to an empty array', () => {
          const tCTIPOREGLA: ITCTIPOREGLA = { idTipoRegla: 123 };
          expectedResult = service.addTCTIPOREGLAToCollectionIfMissing([], tCTIPOREGLA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCTIPOREGLA);
        });

        it('should not add a TCTIPOREGLA to an array that contains it', () => {
          const tCTIPOREGLA: ITCTIPOREGLA = { idTipoRegla: 123 };
          const tCTIPOREGLACollection: ITCTIPOREGLA[] = [
            {
              ...tCTIPOREGLA,
            },
            { idTipoRegla: 456 },
          ];
          expectedResult = service.addTCTIPOREGLAToCollectionIfMissing(tCTIPOREGLACollection, tCTIPOREGLA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TCTIPOREGLA to an array that doesn't contain it", () => {
          const tCTIPOREGLA: ITCTIPOREGLA = { idTipoRegla: 123 };
          const tCTIPOREGLACollection: ITCTIPOREGLA[] = [{ idTipoRegla: 456 }];
          expectedResult = service.addTCTIPOREGLAToCollectionIfMissing(tCTIPOREGLACollection, tCTIPOREGLA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCTIPOREGLA);
        });

        it('should add only unique TCTIPOREGLA to an array', () => {
          const tCTIPOREGLAArray: ITCTIPOREGLA[] = [{ idTipoRegla: 123 }, { idTipoRegla: 456 }, { idTipoRegla: 4763 }];
          const tCTIPOREGLACollection: ITCTIPOREGLA[] = [{ idTipoRegla: 123 }];
          expectedResult = service.addTCTIPOREGLAToCollectionIfMissing(tCTIPOREGLACollection, ...tCTIPOREGLAArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tCTIPOREGLA: ITCTIPOREGLA = { idTipoRegla: 123 };
          const tCTIPOREGLA2: ITCTIPOREGLA = { idTipoRegla: 456 };
          expectedResult = service.addTCTIPOREGLAToCollectionIfMissing([], tCTIPOREGLA, tCTIPOREGLA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tCTIPOREGLA);
          expect(expectedResult).toContain(tCTIPOREGLA2);
        });

        it('should accept null and undefined values', () => {
          const tCTIPOREGLA: ITCTIPOREGLA = { idTipoRegla: 123 };
          expectedResult = service.addTCTIPOREGLAToCollectionIfMissing([], null, tCTIPOREGLA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tCTIPOREGLA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
