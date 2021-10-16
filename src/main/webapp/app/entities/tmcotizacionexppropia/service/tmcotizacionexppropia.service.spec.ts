import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITMCOTIZACIONEXPPROPIA, TMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';

import { TMCOTIZACIONEXPPROPIAService } from './tmcotizacionexppropia.service';

describe('Service Tests', () => {
  describe('TMCOTIZACIONEXPPROPIA Service', () => {
    let service: TMCOTIZACIONEXPPROPIAService;
    let httpMock: HttpTestingController;
    let elemDefault: ITMCOTIZACIONEXPPROPIA;
    let expectedResult: ITMCOTIZACIONEXPPROPIA | ITMCOTIZACIONEXPPROPIA[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TMCOTIZACIONEXPPROPIAService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        idCotizacionExpPropia: 0,
        numero: 0,
        periodo: 0,
        siniestro: 0,
        asegurados: 0,
        valorQx: 0,
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

      it('should create a TMCOTIZACIONEXPPROPIA', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TMCOTIZACIONEXPPROPIA()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TMCOTIZACIONEXPPROPIA', () => {
        const returnedFromService = Object.assign(
          {
            idCotizacionExpPropia: 1,
            numero: 1,
            periodo: 1,
            siniestro: 1,
            asegurados: 1,
            valorQx: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a TMCOTIZACIONEXPPROPIA', () => {
        const patchObject = Object.assign(
          {
            asegurados: 1,
          },
          new TMCOTIZACIONEXPPROPIA()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TMCOTIZACIONEXPPROPIA', () => {
        const returnedFromService = Object.assign(
          {
            idCotizacionExpPropia: 1,
            numero: 1,
            periodo: 1,
            siniestro: 1,
            asegurados: 1,
            valorQx: 1,
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

      it('should delete a TMCOTIZACIONEXPPROPIA', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addTMCOTIZACIONEXPPROPIAToCollectionIfMissing', () => {
        it('should add a TMCOTIZACIONEXPPROPIA to an empty array', () => {
          const tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 123 };
          expectedResult = service.addTMCOTIZACIONEXPPROPIAToCollectionIfMissing([], tMCOTIZACIONEXPPROPIA);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMCOTIZACIONEXPPROPIA);
        });

        it('should not add a TMCOTIZACIONEXPPROPIA to an array that contains it', () => {
          const tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 123 };
          const tMCOTIZACIONEXPPROPIACollection: ITMCOTIZACIONEXPPROPIA[] = [
            {
              ...tMCOTIZACIONEXPPROPIA,
            },
            { idCotizacionExpPropia: 456 },
          ];
          expectedResult = service.addTMCOTIZACIONEXPPROPIAToCollectionIfMissing(tMCOTIZACIONEXPPROPIACollection, tMCOTIZACIONEXPPROPIA);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TMCOTIZACIONEXPPROPIA to an array that doesn't contain it", () => {
          const tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 123 };
          const tMCOTIZACIONEXPPROPIACollection: ITMCOTIZACIONEXPPROPIA[] = [{ idCotizacionExpPropia: 456 }];
          expectedResult = service.addTMCOTIZACIONEXPPROPIAToCollectionIfMissing(tMCOTIZACIONEXPPROPIACollection, tMCOTIZACIONEXPPROPIA);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMCOTIZACIONEXPPROPIA);
        });

        it('should add only unique TMCOTIZACIONEXPPROPIA to an array', () => {
          const tMCOTIZACIONEXPPROPIAArray: ITMCOTIZACIONEXPPROPIA[] = [
            { idCotizacionExpPropia: 123 },
            { idCotizacionExpPropia: 456 },
            { idCotizacionExpPropia: 93768 },
          ];
          const tMCOTIZACIONEXPPROPIACollection: ITMCOTIZACIONEXPPROPIA[] = [{ idCotizacionExpPropia: 123 }];
          expectedResult = service.addTMCOTIZACIONEXPPROPIAToCollectionIfMissing(
            tMCOTIZACIONEXPPROPIACollection,
            ...tMCOTIZACIONEXPPROPIAArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 123 };
          const tMCOTIZACIONEXPPROPIA2: ITMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 456 };
          expectedResult = service.addTMCOTIZACIONEXPPROPIAToCollectionIfMissing([], tMCOTIZACIONEXPPROPIA, tMCOTIZACIONEXPPROPIA2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tMCOTIZACIONEXPPROPIA);
          expect(expectedResult).toContain(tMCOTIZACIONEXPPROPIA2);
        });

        it('should accept null and undefined values', () => {
          const tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA = { idCotizacionExpPropia: 123 };
          expectedResult = service.addTMCOTIZACIONEXPPROPIAToCollectionIfMissing([], null, tMCOTIZACIONEXPPROPIA, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tMCOTIZACIONEXPPROPIA);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
