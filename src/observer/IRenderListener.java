package observer;
import java.util.List;
import strategy.IRender;

public interface IRenderListener {
    void onRender(List<IRender> renderables);
}
