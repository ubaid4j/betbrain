package com.ubaid.app.model.builder.match;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.objects.Match;

public interface IMatchBuilder extends Builder
{
	Match build(long id);
}
