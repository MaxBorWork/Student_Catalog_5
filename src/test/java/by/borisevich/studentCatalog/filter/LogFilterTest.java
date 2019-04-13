package by.borisevich.studentCatalog.filter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class LogFilterTest {
    private HttpServletResponse mockResponse;
    private HttpServletRequest mockRequest;
    private FilterChain filterChain;
    private LogFilter filter;

    @Before
    public void setUp() throws Exception {
        filterChain = Mockito.mock(FilterChain.class);
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        filter = new LogFilter();
    }

    @Test
    public void doFilter() throws ServletException, IOException {
        Mockito.when(mockRequest.getServletPath()).thenReturn("/showStudents");
        filter.init(null);
        filter.doFilter(mockRequest, mockResponse, filterChain);
        filter.destroy();

        Mockito.verify(filterChain).doFilter(mockRequest, mockResponse);
    }
}
